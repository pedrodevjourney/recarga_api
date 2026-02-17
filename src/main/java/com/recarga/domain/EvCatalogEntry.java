package com.recarga.domain;

import java.util.UUID;

public class EvCatalogEntry {

    private final UUID id;
    private final String brand;
    private final String model;
    private final String variant;
    private final int year;
    private final Double batteryCapacityKwh;
    private final Double rangeWltpKm;
    private final Double rangeWltpMiles;

    public EvCatalogEntry(UUID id, String brand, String model, String variant, int year,
                          Double batteryCapacityKwh, Double rangeWltpKm, Double rangeWltpMiles) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.year = year;
        this.batteryCapacityKwh = batteryCapacityKwh;
        this.rangeWltpKm = rangeWltpKm;
        this.rangeWltpMiles = rangeWltpMiles;
    }

    public UUID getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVariant() {
        return variant;
    }

    public int getYear() {
        return year;
    }

    public Double getBatteryCapacityKwh() {
        return batteryCapacityKwh;
    }

    public Double getRangeWltpKm() {
        return rangeWltpKm;
    }

    public Double getRangeWltpMiles() {
        return rangeWltpMiles;
    }
}
