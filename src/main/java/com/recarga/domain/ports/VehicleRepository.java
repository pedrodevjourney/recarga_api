package com.recarga.domain.ports;

import com.recarga.domain.Vehicle;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository {

    Vehicle save(Vehicle vehicle);

    List<Vehicle> findByUserId(UUID userId);
}
