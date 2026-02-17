package com.recarga.application.dto;

import com.recarga.domain.ConnectorType;

import java.util.UUID;

public class VehicleResponse {

    private final UUID id;
    private final UUID userId;
    private final String nickname;
    private final int year;
    private final String brand;
    private final String model;
    private final ConnectorType connectorType;
    private final double batteryCapacityKwh;
    private final String plate;
    private final UUID catalogId;

    public VehicleResponse(UUID id, UUID userId, String nickname, int year, String brand, String model,
                           ConnectorType connectorType, double batteryCapacityKwh, String plate, UUID catalogId) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.connectorType = connectorType;
        this.batteryCapacityKwh = batteryCapacityKwh;
        this.plate = plate;
        this.catalogId = catalogId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public ConnectorType getConnectorType() {
        return connectorType;
    }

    public double getBatteryCapacityKwh() {
        return batteryCapacityKwh;
    }

    public String getPlate() {
        return plate;
    }

    public UUID getCatalogId() {
        return catalogId;
    }
}
