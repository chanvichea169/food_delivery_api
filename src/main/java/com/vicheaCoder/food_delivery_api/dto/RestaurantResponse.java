package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicheaCoder.food_delivery_api.model.BaseEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantResponse extends BaseEntity {

    private Long id;
    private String code;
    private String name;
    private String category;
    private String description;
    private double rating;
    private String address;
    private String phoneNumber;
    @JsonProperty("logo_url")
    private String logUrl;
    @Temporal(TemporalType.TIME)
    @JsonProperty("open_time")
    private String openTime;
    @Temporal(TemporalType.TIME)
    @JsonProperty("close_time")
    private String closeTime;
    @JsonProperty("created_at")
    private String createAt;
    @JsonProperty("create_by")
    private String createBy;
}
