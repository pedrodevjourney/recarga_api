package com.recarga.infrastructure.persistence;

import com.recarga.domain.Vehicle;
import com.recarga.domain.ports.VehicleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleJpaRepository jpaRepository;

    public VehicleRepositoryAdapter(VehicleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleJpaEntity entity = toEntity(vehicle);
        VehicleJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Vehicle> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .toList();
    }

    private VehicleJpaEntity toEntity(Vehicle vehicle) {
        VehicleJpaEntity entity = new VehicleJpaEntity();
        entity.setId(vehicle.getId());
        entity.setUserId(vehicle.getUserId());
        entity.setNickname(vehicle.getNickname());
        entity.setYear(vehicle.getYear());
        entity.setBrand(vehicle.getBrand());
        entity.setModel(vehicle.getModel());
        entity.setConnectorType(vehicle.getConnectorType());
        entity.setBatteryCapacityKwh(vehicle.getBatteryCapacityKwh());
        entity.setPlate(vehicle.getPlate());
        entity.setCatalogId(vehicle.getCatalogId());
        return entity;
    }

    private Vehicle toDomain(VehicleJpaEntity entity) {
        return new Vehicle(
                entity.getId(),
                entity.getUserId(),
                entity.getNickname(),
                entity.getYear(),
                entity.getBrand(),
                entity.getModel(),
                entity.getConnectorType(),
                entity.getBatteryCapacityKwh(),
                entity.getPlate(),
                entity.getCatalogId()
        );
    }
}
