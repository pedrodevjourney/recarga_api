package com.recarga.application.dto;

import com.recarga.application.validation.ValidConnectorType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateVehicleRequest {

    @NotBlank(message = "Apelido é obrigatório")
    @Size(max = 255)
    private String nickname;

    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1990, message = "Ano deve ser a partir de 1990")
    @Max(value = 2030, message = "Ano deve ser até 2030")
    private Integer year;

    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 255)
    private String brand;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 255)
    private String model;

    @NotBlank(message = "Tipo de conector é obrigatório")
    @ValidConnectorType
    private String connectorType;

    @NotNull(message = "Capacidade da bateria é obrigatória")
    @Positive(message = "Capacidade da bateria deve ser positiva")
    private Double batteryCapacityKwh;

    @Size(max = 20)
    private String plate;

    public CreateVehicleRequest() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public Double getBatteryCapacityKwh() {
        return batteryCapacityKwh;
    }

    public void setBatteryCapacityKwh(Double batteryCapacityKwh) {
        this.batteryCapacityKwh = batteryCapacityKwh;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
