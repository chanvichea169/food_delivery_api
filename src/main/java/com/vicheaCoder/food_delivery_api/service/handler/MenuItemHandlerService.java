package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoResponse;
import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import com.vicheaCoder.food_delivery_api.model.MenuItem;
import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuItemHandlerService {

    public MenuItem convertMenuItemRequestToMenuItem(MenuItemRequest menuItemRequest,
                                                     MenuItem menuItem,
                                                     Restaurant restaurant) {
        menuItem.setCode(menuItemRequest.getCode());
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setAvailability(menuItemRequest.getAvailability());
        menuItem.setRestaurant(restaurant);
        menuItem.setCreatedAt(new Date());
        menuItem.setCreatedBy(Constant.SYSTEM_USER);
        return menuItem;
    }

    public MenuItemResponse convertMenuItemToMenuItemResponse(MenuItem menuItem) {
        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItem.getId());
        response.setCode(menuItem.getCode());
        response.setName(menuItem.getName());
        response.setDescription(menuItem.getDescription());
        response.setPrice(menuItem.getPrice());
        response.setAvailability(menuItem.getAvailability());
        response.setRestaurantId(menuItem.getRestaurant().getId());

        if (menuItem.getMenuItemPhotos() != null) {
            List<MenuItemPhotoResponse> photos = menuItem.getMenuItemPhotos().stream()
                    .map(photo -> new MenuItemPhotoResponse(
                            photo.getId(),
                            photo.getFileType(),
                            photo.getFileFormat(),
                            photo.getFileSize(),
                            photo.getFileName(),
                            photo.getSmallUrl(),
                            photo.getMediumUrl(),
                            photo.getLargeUrl(),
                            photo.getUploadBy(),
                            photo.getStatus()
                    ))
                    .toList();
            response.setMenuItemPhotos(photos);
        }

        return response;
    }

    public MenuItemPhoto convertMenuItemPhotoRequestToEntity(MenuItemPhotoRequest request, MenuItem menuItem) {
        MenuItemPhoto photo = new MenuItemPhoto();
        photo.setFileType(request.getFileType());
        photo.setFileFormat(request.getFileFormat());
        photo.setFileSize(request.getFileSize());
        photo.setFileName(request.getFileName());
        photo.setSmallUrl(request.getSmallUrl());
        photo.setMediumUrl(request.getMediumUrl());
        photo.setLargeUrl(request.getLargeUrl());
        photo.setUploadBy(request.getUploadBy());
        photo.setStatus(request.getStatus());
        photo.setMenuItem(menuItem);
        if (photo.getId() == null) {
            photo.setCreatedAt(new Date());
            photo.setUploadBy(Constant.SYSTEM_USER);
        }
        return photo;
    }

}
