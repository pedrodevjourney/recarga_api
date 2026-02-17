package com.recarga.infrastructure.openev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recarga.application.openev.dto.OpenEVDatasetDto;
import com.recarga.application.openev.dto.OpenEVVehicleEntryDto;
import com.recarga.domain.EvCatalogEntry;
import com.recarga.domain.ports.EvCatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OpenEVDataLoader {

    private static final String RESOURCE_PATH = "open-ev-data.json";
    private static final Logger log = LoggerFactory.getLogger(OpenEVDataLoader.class);

    private final EvCatalogRepository evCatalogRepository;
    private final ObjectMapper objectMapper;

    public OpenEVDataLoader(EvCatalogRepository evCatalogRepository, ObjectMapper objectMapper) {
        this.evCatalogRepository = evCatalogRepository;
        this.objectMapper = objectMapper;
    }

    @Order(1)
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if (evCatalogRepository.count() > 0) {
            log.debug("EV catalog already populated, skipping OpenEV data load");
            return;
        }
        ClassPathResource resource = new ClassPathResource(RESOURCE_PATH);
        if (!resource.exists()) {
            log.warn("OpenEV data file not found at classpath:{}. Place open-ev-data.json in src/main/resources or download from https://github.com/open-ev-data/open-ev-data-dataset/releases", RESOURCE_PATH);
            return;
        }
        try (InputStream is = resource.getInputStream()) {
            loadFromStream(is);
        } catch (Exception e) {
            log.error("Failed to load OpenEV data from {}", RESOURCE_PATH, e);
        }
    }

    @Transactional
    protected void loadFromStream(InputStream is) throws Exception {
        OpenEVDatasetDto dataset = objectMapper.readValue(is, OpenEVDatasetDto.class);
        List<EvCatalogEntry> entries = new ArrayList<>();
        if (dataset.getVehicles() != null) {
            for (OpenEVVehicleEntryDto dto : dataset.getVehicles()) {
                EvCatalogEntry entry = mapToEntry(dto);
                if (entry != null) {
                    entries.add(entry);
                }
            }
        }
        if (!entries.isEmpty()) {
            evCatalogRepository.saveAll(entries);
            log.info("Loaded {} vehicles from OpenEV data into ev_catalog", entries.size());
        }
    }

    private EvCatalogEntry mapToEntry(OpenEVVehicleEntryDto dto) {
        String brand = dto.getBrand();
        if (brand == null && dto.getMake() != null) {
            brand = dto.getMake().getName();
        }
        String model = dto.getModel();
        Integer year = dto.getYear();
        if (brand == null || brand.isBlank() || model == null || model.isBlank() || year == null) {
            return null;
        }
        Double batteryKwh = null;
        if (dto.getBattery() != null) {
            batteryKwh = dto.getBattery().getCapacity_kwh();
        }
        Double rangeKm = null;
        Double rangeMiles = null;
        if (dto.getRange() != null && dto.getRange().getWltp() != null) {
            rangeKm = dto.getRange().getWltp().getKm();
            rangeMiles = dto.getRange().getWltp().getMiles();
        }
        return new EvCatalogEntry(
                UUID.randomUUID(),
                brand.trim(),
                model.trim(),
                dto.getVariant() != null ? dto.getVariant().trim() : null,
                year,
                batteryKwh,
                rangeKm,
                rangeMiles
        );
    }
}
