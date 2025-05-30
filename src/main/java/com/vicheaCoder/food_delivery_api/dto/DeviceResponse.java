package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

    private Long id;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("device_model")
    private String deviceModel;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("app_version")
    private String appVersion;
    @JsonProperty("last_login")
    private Date lastLogin;
    @JsonProperty("trust_device")
    private boolean trustDevice;
    @JsonProperty("status")
    private String status;

}