package com.recarga.application.openev.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenEVDatasetDto {

    private String version;

    @JsonProperty("generated_at")
    private String generatedAt;

    private List<OpenEVVehicleEntryDto> vehicles;

    public OpenEVDatasetDto() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    public List<OpenEVVehicleEntryDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<OpenEVVehicleEntryDto> vehicles) {
        this.vehicles = vehicles;
    }
}
