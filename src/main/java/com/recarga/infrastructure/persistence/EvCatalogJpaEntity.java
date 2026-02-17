package com.recarga.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "ev_catalog")
public class EvCatalogJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 255)
    private String brand;

    @Column(nullable = false, length = 255)
    private String model;

    @Column(length = 255)
    private String variant;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "battery_capacity_kwh")
    private Double batteryCapacityKwh;

    @Column(name = "range_wltp_km")
    private Double rangeWltpKm;

    @Column(name = "range_wltp_miles")
    private Double rangeWltpMiles;

    public EvCatalogJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getBatteryCapacityKwh() {
        return batteryCapacityKwh;
    }

    public void setBatteryCapacityKwh(Double batteryCapacityKwh) {
        this.batteryCapacityKwh = batteryCapacityKwh;
    }

    public Double getRangeWltpKm() {
        return rangeWltpKm;
    }

    public void setRangeWltpKm(Double rangeWltpKm) {
        this.rangeWltpKm = rangeWltpKm;
    }

    public Double getRangeWltpMiles() {
        return rangeWltpMiles;
    }

    public void setRangeWltpMiles(Double rangeWltpMiles) {
        this.rangeWltpMiles = rangeWltpMiles;
    }
}
