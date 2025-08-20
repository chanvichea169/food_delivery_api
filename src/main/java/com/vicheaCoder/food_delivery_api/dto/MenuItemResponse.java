package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private double price;

    @JsonProperty("availability")
    private Integer availability;

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("menu_item_photo")
    private List<MenuItemPhotoResponse> menuItemPhotos;
}
