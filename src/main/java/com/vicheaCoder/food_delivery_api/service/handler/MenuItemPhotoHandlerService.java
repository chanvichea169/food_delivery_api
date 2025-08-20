package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoResponse;
import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MenuItemPhotoHandlerService {
    public String uploadFile(String fileName) {
        // Simulate file upload logic
        // upload file to server or cloud storage write here
        return Constant.SUCCESS;
    }

    public MenuItemPhoto convertItemPhotoRequestToItemPhoto(MenuItemPhotoRequest menuItemPhotoRequest, MenuItemPhoto menuItemPhoto) {
        menuItemPhoto.setFileType(menuItemPhotoRequest.getFileType());
        menuItemPhoto.setFileFormat(menuItemPhotoRequest.getFileFormat());
        menuItemPhoto.setFileSize(menuItemPhotoRequest.getFileSize());
        menuItemPhoto.setFileName(menuItemPhotoRequest.getFileName());
        menuItemPhoto.setSmallUrl(menuItemPhotoRequest.getSmallUrl());
        menuItemPhoto.setMediumUrl(menuItemPhotoRequest.getMediumUrl());
        menuItemPhoto.setLargeUrl(menuItemPhotoRequest.getLargeUrl());
        menuItemPhoto.setUploadBy(menuItemPhotoRequest.getUploadBy());
        menuItemPhoto.setStatus(menuItemPhotoRequest.getStatus());
        if (menuItemPhoto.getId() == null) {
            menuItemPhoto.setCreatedAt(new Date());
            menuItemPhoto.setUploadBy(Constant.SYSTEM_USER);
        }
        return menuItemPhoto;
    }

    public MenuItemPhotoResponse convertItemPhotoToItemPhotoResponse(MenuItemPhoto menuItemPhoto) {
        return MenuItemPhotoResponse.builder()
                .id(menuItemPhoto.getId())
                .fileType(menuItemPhoto.getFileType())
                .fileFormat(menuItemPhoto.getFileFormat())
                .fileSize(menuItemPhoto.getFileSize())
                .fileName(menuItemPhoto.getFileName())
                .smallUrl(menuItemPhoto.getSmallUrl())
                .mediumUrl(menuItemPhoto.getMediumUrl())
                .largeUrl(menuItemPhoto.getLargeUrl())
                .uploadBy(menuItemPhoto.getUploadBy())
                .status(menuItemPhoto.getStatus())
                .build();
    }
}
