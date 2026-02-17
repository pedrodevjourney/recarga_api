package com.recarga.application;

import com.recarga.application.dto.EvCatalogResponse;
import com.recarga.domain.EvCatalogEntry;
import com.recarga.domain.ports.EvCatalogRepository;

import java.util.List;

public class ListEvCatalog {

    private final EvCatalogRepository evCatalogRepository;

    public ListEvCatalog(EvCatalogRepository evCatalogRepository) {
        this.evCatalogRepository = evCatalogRepository;
    }

    public List<EvCatalogResponse> execute(String brand, Integer year, String model, int page, int size) {
        List<EvCatalogEntry> entries = evCatalogRepository.findAll(brand, year, model, page, size);
        return entries.stream().map(this::toResponse).toList();
    }

    private EvCatalogResponse toResponse(EvCatalogEntry entry) {
        return new EvCatalogResponse(
                entry.getId(),
                entry.getBrand(),
                entry.getModel(),
                entry.getVariant(),
                entry.getYear(),
                entry.getBatteryCapacityKwh(),
                entry.getRangeWltpKm(),
                entry.getRangeWltpMiles()
        );
    }
}
