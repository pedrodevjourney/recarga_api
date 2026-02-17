package com.recarga.infrastructure.persistence;

import com.recarga.domain.EvCatalogEntry;
import com.recarga.domain.ports.EvCatalogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EvCatalogRepositoryAdapter implements EvCatalogRepository {

    private final EvCatalogJpaRepository jpaRepository;

    public EvCatalogRepositoryAdapter(EvCatalogJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public void saveAll(List<EvCatalogEntry> entries) {
        List<EvCatalogJpaEntity> entities = entries.stream().map(this::toEntity).toList();
        jpaRepository.saveAll(entities);
    }

    @Override
    public Optional<EvCatalogEntry> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<EvCatalogEntry> findAll(String brand, Integer year, String model, int page, int size) {
        String modelFilter = (model != null && model.isBlank()) ? null : model;
        return jpaRepository.findAllFiltered(brand, year, modelFilter, PageRequest.of(page, size))
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private EvCatalogJpaEntity toEntity(EvCatalogEntry entry) {
        EvCatalogJpaEntity entity = new EvCatalogJpaEntity();
        entity.setId(entry.getId());
        entity.setBrand(entry.getBrand());
        entity.setModel(entry.getModel());
        entity.setVariant(entry.getVariant());
        entity.setYear(entry.getYear());
        entity.setBatteryCapacityKwh(entry.getBatteryCapacityKwh());
        entity.setRangeWltpKm(entry.getRangeWltpKm());
        entity.setRangeWltpMiles(entry.getRangeWltpMiles());
        return entity;
    }

    private EvCatalogEntry toDomain(EvCatalogJpaEntity entity) {
        return new EvCatalogEntry(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getVariant(),
                entity.getYear(),
                entity.getBatteryCapacityKwh(),
                entity.getRangeWltpKm(),
                entity.getRangeWltpMiles()
        );
    }
}
