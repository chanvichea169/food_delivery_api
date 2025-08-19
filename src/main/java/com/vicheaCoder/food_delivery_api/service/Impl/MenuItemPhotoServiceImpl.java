package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoResponse;
import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import com.vicheaCoder.food_delivery_api.respository.MenuItemPhotoRepository;
import com.vicheaCoder.food_delivery_api.service.MenuItemPhotoService;
import com.vicheaCoder.food_delivery_api.service.handler.MenuItemPhotoHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemPhotoServiceImpl implements MenuItemPhotoService {

    private final MenuItemPhotoRepository menuItemPhotoRepository;
    private final MenuItemPhotoHandlerService menuItemPhotoHandlerService;

    @Override
    public MenuItemPhotoResponse upload(MenuItemPhotoRequest menuItemPhotoRequest) {
        if (Constant.SUCCESS.equalsIgnoreCase(menuItemPhotoHandlerService.uploadFile(menuItemPhotoRequest.getFileName()))) {

            log.info("File uploaded successfully: {}", menuItemPhotoRequest.getFileName());

            MenuItemPhoto menuItemPhoto = new MenuItemPhoto();
            menuItemPhoto = menuItemPhotoHandlerService.convertItemPhotoRequestToItemPhoto(menuItemPhotoRequest, menuItemPhoto);
            menuItemPhoto = menuItemPhotoRepository.save(menuItemPhoto);

            log.info("MenuItemPhoto saved successfully with ID: {}", menuItemPhoto.getId());

            return menuItemPhotoHandlerService.convertItemPhotoToItemPhotoResponse(menuItemPhoto);
        } else {
            log.error("File upload failed: {}", menuItemPhotoRequest.getFileName());
            throw new RuntimeException("File upload failed");
        }
    }

    @Override
    public MenuItemPhotoResponse update(MenuItemPhotoRequest menuItemPhotoRequest, Long id) {
        MenuItemPhoto existingPhoto = menuItemPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItemPhoto not found with ID: " + id));

        if (Constant.SUCCESS.equalsIgnoreCase(menuItemPhotoHandlerService.uploadFile(menuItemPhotoRequest.getFileName()))) {
            log.info("File uploaded successfully for update: {}", menuItemPhotoRequest.getFileName());

            existingPhoto = menuItemPhotoHandlerService.convertItemPhotoRequestToItemPhoto(menuItemPhotoRequest, existingPhoto);
            existingPhoto = menuItemPhotoRepository.save(existingPhoto);

            log.info("MenuItemPhoto updated successfully with ID: {}", existingPhoto.getId());

            return menuItemPhotoHandlerService.convertItemPhotoToItemPhotoResponse(existingPhoto);
        } else {
            log.error("File upload failed during update: {}", menuItemPhotoRequest.getFileName());
            throw new RuntimeException("File upload failed during update");
        }
    }

    @Override
    public MenuItemPhotoResponse getById(Long id) {
        MenuItemPhoto menuItemPhoto = menuItemPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItemPhoto not found with ID: " + id));

        return menuItemPhotoHandlerService.convertItemPhotoToItemPhotoResponse(menuItemPhoto);
    }

    @Override
    public void delete(Long id) {
        MenuItemPhoto menuItemPhoto = menuItemPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItemPhoto not found with ID: " + id));

        menuItemPhotoRepository.delete(menuItemPhoto);
        log.info("MenuItemPhoto deleted successfully with ID: {}", id);
    }
}

