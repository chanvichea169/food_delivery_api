package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import com.vicheaCoder.food_delivery_api.exception.ResourceNotFoundException;
import com.vicheaCoder.food_delivery_api.model.MenuItem;
import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import com.vicheaCoder.food_delivery_api.respository.MenuItemPhotoRepository;
import com.vicheaCoder.food_delivery_api.respository.MenuItemRepository;
import com.vicheaCoder.food_delivery_api.respository.RestaurantRepository;
import com.vicheaCoder.food_delivery_api.service.MenuItemService;
import com.vicheaCoder.food_delivery_api.service.handler.MenuItemHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemHandlerService menuItemHandlerService;
    private final MenuItemPhotoRepository menuItemPhotoRepository;

    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(menuItemRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Restaurant not found with id: " + menuItemRequest.getRestaurantId()
                ));

        MenuItem menuItem = menuItemHandlerService.convertMenuItemRequestToMenuItem(
                menuItemRequest, new MenuItem(), restaurant
        );

        if (menuItemRequest.getMenuItemPhotoRequest() != null && !menuItemRequest.getMenuItemPhotoRequest().isEmpty()) {
            menuItemRequest.getMenuItemPhotoRequest().forEach(photoReq -> {
                MenuItemPhoto photo = menuItemHandlerService.convertMenuItemPhotoRequestToEntity(photoReq, menuItem);
                menuItem.addPhoto(photo);
            });
        }

        MenuItem savedItem = menuItemRepository.save(menuItem);

        savedItem.getMenuItemPhotos().forEach(photo -> {
            MenuItemPhoto save = menuItemPhotoRepository.save(photo);
        });

        return menuItemHandlerService.convertMenuItemToMenuItemResponse(savedItem);
    }


    @Override
    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {

        MenuItem existingItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Restaurant not found with id: " + request.getRestaurantId()
                ));

        existingItem.setCode(request.getCode());
        existingItem.setName(request.getName());
        existingItem.setDescription(request.getDescription());
        existingItem.setPrice(request.getPrice());
        existingItem.setAvailability(request.getAvailability());
        existingItem.setRestaurant(restaurant);

        existingItem.getMenuItemPhotos().clear();
        if (request.getMenuItemPhotoRequest() != null && !request.getMenuItemPhotoRequest().isEmpty()) {
            request.getMenuItemPhotoRequest().forEach(photoReq -> {
                MenuItemPhoto photo = menuItemHandlerService.convertMenuItemPhotoRequestToEntity(photoReq, existingItem);
                existingItem.addPhoto(photo);
            });
        }

        MenuItem savedItem = menuItemRepository.save(existingItem);

        return menuItemHandlerService.convertMenuItemToMenuItemResponse(savedItem);
    }



    @Override
    public MenuItemResponse getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .map(menuItemHandlerService::convertMenuItemToMenuItemResponse)
                .orElseGet(() -> {
                    log.error("Menu item with id {} not found", id);
                    return new MenuItemResponse();
                });
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
        log.info("Menu item with ID: {} deleted successfully", id);
    }

    @Override
    public List<MenuItemResponse> getAllMenuItemsByRestaurantId(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurant(restaurant);
        log.info("Found {} menu items for restaurant with id {}", menuItems.size(), restaurantId);

        return menuItems.stream()
                .map(menuItemHandlerService::convertMenuItemToMenuItemResponse)
                .toList();
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        log.info("Found {} menu items in total", menuItems.size());

        return menuItems.stream()
                .map(menuItemHandlerService::convertMenuItemToMenuItemResponse)
                .toList();
    }
}
