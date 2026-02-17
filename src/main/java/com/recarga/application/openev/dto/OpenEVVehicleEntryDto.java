package com.recarga.application.openev.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenEVVehicleEntryDto {

    private String brand;
    /** Alternative in some OpenEV formats: make.name */
    private OpenEVMakeDto make;
    private String model;
    private String variant;
    private Integer year;
    private OpenEVBatteryDto battery;
    private OpenEVRangeDto range;

    public OpenEVVehicleEntryDto() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public OpenEVMakeDto getMake() {
        return make;
    }

    public void setMake(OpenEVMakeDto make) {
        this.make = make;
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

    public OpenEVBatteryDto getBattery() {
        return battery;
    }

    public void setBattery(OpenEVBatteryDto battery) {
        this.battery = battery;
    }

    public OpenEVRangeDto getRange() {
        return range;
    }

    public void setRange(OpenEVRangeDto range) {
        this.range = range;
    }
}
