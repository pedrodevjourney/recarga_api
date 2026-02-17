package com.recarga.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class AddVehicleFromCatalogRequest {

    @NotNull(message = "ID do catálogo é obrigatório")
    private UUID catalogId;

    @NotNull(message = "Apelido é obrigatório")
    @Size(min = 1, max = 255)
    private String nickname;

    @Size(max = 20)
    private String plate;

    public AddVehicleFromCatalogRequest() {
    }

    public UUID getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(UUID catalogId) {
        this.catalogId = catalogId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
