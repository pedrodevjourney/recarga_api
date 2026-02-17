package com.recarga.application.openev.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenEVBatteryDto {

    @JsonAlias("pack_capacity_kwh_net")
    private Double capacity_kwh;

    public OpenEVBatteryDto() {
    }

    public Double getCapacity_kwh() {
        return capacity_kwh;
    }

    public void setCapacity_kwh(Double capacity_kwh) {
        this.capacity_kwh = capacity_kwh;
    }
}
