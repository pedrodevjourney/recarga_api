package com.recarga.application;

import com.recarga.application.dto.AddVehicleFromCatalogRequest;
import com.recarga.application.dto.VehicleResponse;
import com.recarga.application.exceptions.CatalogEntryNotFoundException;
import com.recarga.domain.ConnectorType;
import com.recarga.domain.EvCatalogEntry;
import com.recarga.domain.Vehicle;
import com.recarga.domain.ports.EvCatalogRepository;
import com.recarga.domain.ports.VehicleRepository;

import java.util.UUID;

public class AddVehicleFromCatalog {

    private final VehicleRepository vehicleRepository;
    private final EvCatalogRepository evCatalogRepository;

    public AddVehicleFromCatalog(VehicleRepository vehicleRepository, EvCatalogRepository evCatalogRepository) {
        this.vehicleRepository = vehicleRepository;
        this.evCatalogRepository = evCatalogRepository;
    }

    public VehicleResponse execute(AddVehicleFromCatalogRequest request, UUID userId) {
        EvCatalogEntry catalog = evCatalogRepository.findById(request.getCatalogId())
                .orElseThrow(CatalogEntryNotFoundException::new);

        double batteryKwh = catalog.getBatteryCapacityKwh() != null ? catalog.getBatteryCapacityKwh() : 0.0;
        Vehicle vehicle = new Vehicle(
                UUID.randomUUID(),
                userId,
                request.getNickname().trim(),
                catalog.getYear(),
                catalog.getBrand(),
                catalog.getModel(),
                ConnectorType.TYPE_2,
                batteryKwh,
                request.getPlate() != null ? request.getPlate().trim() : null,
                catalog.getId()
        );
        Vehicle saved = vehicleRepository.save(vehicle);
        return new VehicleResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getNickname(),
                saved.getYear(),
                saved.getBrand(),
                saved.getModel(),
                saved.getConnectorType(),
                saved.getBatteryCapacityKwh(),
                saved.getPlate(),
                saved.getCatalogId()
        );
    }
}
