package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemResponse {

    private Long id;
    private String code;
    private String name;
    private String description;
    private double price;
    private Integer availability;
    @JsonProperty("restaurant_id")
    private Long restaurantId;
}
