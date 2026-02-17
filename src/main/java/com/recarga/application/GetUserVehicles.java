package com.recarga.application;

import com.recarga.application.dto.VehicleResponse;
import com.recarga.domain.Vehicle;
import com.recarga.domain.ports.VehicleRepository;

import java.util.List;
import java.util.UUID;

public class GetUserVehicles {

    private final VehicleRepository vehicleRepository;

    public GetUserVehicles(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleResponse> execute(UUID userId) {
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        return vehicles.stream()
                .map(this::toResponse)
                .toList();
    }

    private VehicleResponse toResponse(Vehicle vehicle) {
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
