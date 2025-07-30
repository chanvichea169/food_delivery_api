package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.dto.DeviceRequest;
import com.vicheaCoder.food_delivery_api.dto.UserRequest;
import com.vicheaCoder.food_delivery_api.dto.UserResponse;
import com.vicheaCoder.food_delivery_api.enumeration.UserType;
import com.vicheaCoder.food_delivery_api.exception.ResourceNotFoundException;
import com.vicheaCoder.food_delivery_api.model.Device;
import com.vicheaCoder.food_delivery_api.model.User;
import com.vicheaCoder.food_delivery_api.respository.DeviceRepository;
import com.vicheaCoder.food_delivery_api.respository.UserRepository;
import com.vicheaCoder.food_delivery_api.service.UserService;
import com.vicheaCoder.food_delivery_api.service.handler.UserHandlerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.vicheaCoder.food_delivery_api.utils.DateTimeUtils.convertStringToDate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserHandlerService userHandlerService;

    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           DeviceRepository deviceRepository,
                           UserHandlerService userHandlerService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userHandlerService = userHandlerService;
    }

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User user = userHandlerService.convertUserRequestToUser(userRequest);
        log.info("Saved user with username: {}", user.getUsername());

        userRepository.save(user);

        if(user.getId() != null) {
            Device device = userHandlerService.convertDeviceRequestToDevice(userRequest.getDeviceRequest(), user);
            device.setUser(user);
            deviceRepository.save(device);
            log.info("Saved device with ID: {}", device);
        } else {
            log.error("Failed to create user, ID is null");
            throw new RuntimeException("Failed to create user, ID is null");
        }

        return null;
    }
    @Transactional
    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            log.error("User with ID {} not found", id);
            throw new RuntimeException("User with ID " + id + " not found");
        }

        User user = optionalUser.get();

        userHandlerService.validateUsername(userRequest.getUsername());
        userHandlerService.validatePhoneNumber(userRequest.getPhoneNumber());
        userHandlerService.validateEmail(userRequest.getEmail());

        user.setUsername(userRequest.getUsername());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setGender(userRequest.getGender());
        user.setUserType(UserType.valueOf(
                StringUtils.hasText(userRequest.getUserType()) ? userRequest.getUserType().toUpperCase() : "CUSTOMER"));
        user.setAddress(userRequest.getAddress());
        user.setStatus(userRequest.getStatus());
        user.setDateOfBirth(convertStringToDate(userRequest.getDateOfBirth()));
        user.setUpdatedAt(new Date());
        user.setUpdatedBy("SYSTEM");

        User updatedUser = userRepository.save(user);
        log.info("Updated user with ID: {}", id);

        DeviceRequest deviceRequest = userRequest.getDeviceRequest();
        if (deviceRequest != null && StringUtils.hasText(deviceRequest.getDeviceId())) {
            Device existingDevice = user.getDevices().stream()
                    .filter(d -> d.getDeviceId().equals(deviceRequest.getDeviceId()))
                    .findFirst()
                    .orElse(null);

            if (existingDevice != null) {
                existingDevice.setDeviceType(deviceRequest.getDeviceType());
                existingDevice.setDeviceModel(deviceRequest.getDeviceModel());
                existingDevice.setOsVersion(deviceRequest.getOsVersion());
                existingDevice.setAppVersion(deviceRequest.getAppVersion());
                existingDevice.setLastLogin(deviceRequest.getLastLogin());
                existingDevice.setTrustDevice(deviceRequest.isTrustDevice());
                existingDevice.setStatus(deviceRequest.getStatus());
                existingDevice.setUser(updatedUser);
                existingDevice.setUpdatedAt(new Date());
                existingDevice.setUpdatedBy("SYSTEM");
                existingDevice.setCreatedBy(existingDevice.getCreatedBy());
                existingDevice.setCreatedAt(existingDevice.getCreatedAt());

                try {
                    deviceRepository.save(existingDevice);
                    log.info("Updated existing device for user: {}", updatedUser.getUsername());
                } catch (Exception e) {
                    log.error("Failed to update device: {}", e.getMessage(), e);
                }
            } else {
                Device newDevice = userHandlerService.convertDeviceRequestToDevice(deviceRequest, updatedUser);
                newDevice.setUser(updatedUser);

                try {
                    deviceRepository.save(newDevice);
                    log.info("Added new device for user: {}", updatedUser.getUsername());
                } catch (Exception e) {
                    log.error("Failed to add new device: {}", e.getMessage(), e);
                }
            }
        } else {
            log.warn("No valid device ID provided. Skipping device update.");
        }

        return modelMapper.map(updatedUser, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User with ID {} not found", id);
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        log.info("Fetching user with ID: {}", id);
        return userHandlerService.convertToUserResponse(user.get());
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User with ID {} not found", id);
            throw new RuntimeException("User with ID " + id + " not found");
        }
        log.info("Deleting user with ID: {}", id);
        userRepository.delete(user.get());

        deviceRepository.deleteAll(user.get().getDevices());
        log.info("Deleted devices associated with user ID: {}", id);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        log.info("Fetching all users, total count: {}", users.size());
        return users.stream()
                .map(userHandlerService::convertToUserResponse)
                .toList();
    }
}


