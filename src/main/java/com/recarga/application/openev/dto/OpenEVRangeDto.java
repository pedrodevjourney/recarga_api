package com.recarga.application.openev.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenEVRangeDto {

    private OpenEVWltpDto wltp;

    public OpenEVRangeDto() {
    }

    public OpenEVWltpDto getWltp() {
        return wltp;
    }

    public void setWltp(OpenEVWltpDto wltp) {
        this.wltp = wltp;
    }
}
