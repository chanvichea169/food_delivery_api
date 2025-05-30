package com.vicheaCoder.food_delivery_api.respository;


import com.vicheaCoder.food_delivery_api.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {
}
