package com.recarga.domain.ports;

import com.recarga.domain.EvCatalogEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvCatalogRepository {

    long count();

    void saveAll(List<EvCatalogEntry> entries);

    Optional<EvCatalogEntry> findById(UUID id);

    /**
     * Find catalog entries with optional filters. Page and size are 0-based and limit count.
     */
    List<EvCatalogEntry> findAll(String brand, Integer year, String model, int page, int size);
}
