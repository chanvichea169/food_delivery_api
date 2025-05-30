package com.vicheaCoder.food_delivery_api.respository;


import com.vicheaCoder.food_delivery_api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
