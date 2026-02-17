package com.recarga.application;

import com.recarga.application.dto.CreateVehicleRequest;
import com.recarga.application.dto.VehicleResponse;
import com.recarga.domain.ConnectorType;
import com.recarga.domain.Vehicle;
import com.recarga.domain.ports.VehicleRepository;

import java.util.UUID;

public class CreateVehicle {

    private final VehicleRepository vehicleRepository;

    public CreateVehicle(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleResponse execute(CreateVehicleRequest request, UUID userId) {
        Vehicle vehicle = new Vehicle(
                UUID.randomUUID(),
                userId,
                request.getNickname().trim(),
                request.getYear(),
                request.getBrand().trim(),
                request.getModel().trim(),
                ConnectorType.valueOf(request.getConnectorType()),
                request.getBatteryCapacityKwh(),
                request.getPlate() != null ? request.getPlate().trim() : null,
                null
        );
        Vehicle saved = vehicleRepository.save(vehicle);
        return toResponse(saved);
    }

    private static VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getUserId(),
                vehicle.getNickname(),
                vehicle.getYear(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getConnectorType(),
                vehicle.getBatteryCapacityKwh(),
                vehicle.getPlate(),
                vehicle.getCatalogId()
        );
    }
}
