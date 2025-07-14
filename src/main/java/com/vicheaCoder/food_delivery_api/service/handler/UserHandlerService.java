package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.dto.DeviceRequest;
import com.vicheaCoder.food_delivery_api.dto.DeviceResponse;
import com.vicheaCoder.food_delivery_api.dto.UserRequest;
import com.vicheaCoder.food_delivery_api.dto.UserResponse;
import com.vicheaCoder.food_delivery_api.enumeration.UserType;
import com.vicheaCoder.food_delivery_api.model.Device;
import com.vicheaCoder.food_delivery_api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.vicheaCoder.food_delivery_api.constant.Constant.*;
import static com.vicheaCoder.food_delivery_api.utils.DateTimeUtils.convertStringToDate;

@Service
@Slf4j
public class UserHandlerService {

    public void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            log.error("Username is null or empty");
            throw new IllegalArgumentException("Username must not be null or empty");
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            log.error("Username contains invalid characters: {}", username);
            throw new IllegalArgumentException("Username contains invalid characters");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            log.error("Phone number is null or empty");
            throw new IllegalArgumentException("Phone number must not be null or empty");
        }

        if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
            log.error("Phone number format is invalid: {}", phoneNumber);
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            log.error("Email is null or empty");
            throw new IllegalArgumentException("Email must not be null or empty");
        }

        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            log.error("Email format is invalid: {}", email);
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public UserResponse convertToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .address(user.getAddress())
                .userType(user.getUserType() != null ? user.getUserType().name() : null)
                .gender(user.getGender())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth() != null ? user.getDateOfBirth().toString() : null)
                .status(user.getStatus())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .updatedBy(user.getUpdatedBy())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User convertUserRequestToUser(final UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setGender(userRequest.getGender());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAddress(userRequest.getAddress());

        String userTypeStr = userRequest.getUserType();
        if (userTypeStr == null || userTypeStr.isBlank()) {
            userTypeStr = "CUSTOMER";
        }
        user.setUserType(UserType.valueOf(userTypeStr.toUpperCase()));
        user.setStatus(USER_STATUS_ACTIVE);
        user.setDateOfBirth(convertStringToDate(userRequest.getDateOfBirth()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setCreatedBy(SYSTEM_USER);
        user.setUpdatedBy(SYSTEM_USER);
        return user;
    }

    public Device convertDeviceRequestToDevice(final DeviceRequest deviceRequest, final User user) {
        Device device = new Device();
        device.setDeviceId(deviceRequest.getDeviceId());
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setDeviceModel(deviceRequest.getDeviceModel());
        device.setOsVersion(deviceRequest.getOsVersion());
        device.setAppVersion(deviceRequest.getAppVersion());
        device.setLastLogin(deviceRequest.getLastLogin());
        device.setTrustDevice(deviceRequest.isTrustDevice());
        device.setStatus(deviceRequest.getStatus());
        device.setUser(user);
        return device;
    }

    public DeviceResponse convertToDeviceResponse(final Device device) {
        return DeviceResponse.builder()
                .deviceId(device.getDeviceId())
                .deviceType(device.getDeviceType())
                .deviceModel(device.getDeviceModel())
                .osVersion(device.getOsVersion())
                .appVersion(device.getAppVersion())
                .lastLogin(device.getLastLogin())
                .trustDevice(device.isTrustDevice())
                .status(device.getStatus())
                .build();
    }

}
