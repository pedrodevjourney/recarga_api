package com.recarga.infrastructure.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EvCatalogJpaRepository extends JpaRepository<EvCatalogJpaEntity, UUID> {

    @Query("SELECT e FROM EvCatalogJpaEntity e WHERE (:brand IS NULL OR e.brand = :brand) AND (:year IS NULL OR e.year = :year) AND (:model IS NULL OR LOWER(e.model) LIKE LOWER(CONCAT('%', :model, '%')))")
    List<EvCatalogJpaEntity> findAllFiltered(@Param("brand") String brand, @Param("year") Integer year, @Param("model") String model, Pageable pageable);
}
