package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemRequest {

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
    private List<MenuItemPhotoRequest> menuItemPhotoRequest;
}
