package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerRequest;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerResponse;
import com.vicheaCoder.food_delivery_api.dto.DeviceResponse;
import com.vicheaCoder.food_delivery_api.enumeration.VehicleType;
import com.vicheaCoder.food_delivery_api.model.DeliveryPartner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.vicheaCoder.food_delivery_api.utils.DateTimeUtils.convertStringToDate;

@Service
@Slf4j
public class DeliveryPartnerHandlerService {

    public DeliveryPartner convertDeliveryPartnerRequestToDeliveryPartner(
            DeliveryPartner deliveryPartner,
            DeliveryPartnerRequest deliveryPartnerRequest) {

        deliveryPartner.setUsername(deliveryPartnerRequest.getUsername());
        deliveryPartner.setFirstname(deliveryPartnerRequest.getFirstName());
        deliveryPartner.setLastname(deliveryPartnerRequest.getLastName());
        deliveryPartner.setPassword(deliveryPartnerRequest.getPassword());
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setPhoneNumber(deliveryPartnerRequest.getPhoneNumber());
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());
        deliveryPartner.setVehicleType(VehicleType.valueOf(
                (deliveryPartnerRequest.getVehicleType() != null && !deliveryPartnerRequest.getVehicleType().isEmpty()) ?
                        deliveryPartnerRequest.getVehicleType().toUpperCase() :
                        "MOTORBIKE"
        ));
        deliveryPartner.setAvailable(deliveryPartnerRequest.isAvailable());
        deliveryPartner.setDateOfBirth(convertStringToDate(deliveryPartnerRequest.getDateOfBirth()));
        if(deliveryPartner.getId() == null){
            deliveryPartner.setCreatedAt(new Date());
            deliveryPartner.setCreatedBy(Constant.SYSTEM_USER);
        }

        return deliveryPartner;
    }

    public DeliveryPartnerResponse convertDeliveryPartnerToDeliveryPartnerResponse(
            DeliveryPartner deliveryPartner) {

        DeliveryPartnerResponse deliveryPartnerResponse = new DeliveryPartnerResponse();
        deliveryPartnerResponse.setId(deliveryPartner.getId());
        deliveryPartnerResponse.setUsername(deliveryPartner.getUsername());
        deliveryPartnerResponse.setFirstName(deliveryPartner.getFirstname());
        deliveryPartnerResponse.setLastName(deliveryPartner.getLastname());
        deliveryPartnerResponse.setPassword(deliveryPartner.getPassword());
        deliveryPartnerResponse.setEmail(deliveryPartner.getEmail());
        deliveryPartnerResponse.setPhoneNumber(deliveryPartner.getPhoneNumber());
        deliveryPartnerResponse.setAddress(deliveryPartner.getAddress());
        deliveryPartnerResponse.setGender(deliveryPartner.getGender());
        deliveryPartnerResponse.setVehicleType(deliveryPartner.getVehicleType() != null ?
                deliveryPartner.getVehicleType().name() : "MOTORBIKE");
        deliveryPartnerResponse.setAvailable(deliveryPartner.isAvailable());
        deliveryPartnerResponse.setDateOfBirth(deliveryPartner.getDateOfBirth().toString());
        deliveryPartnerResponse.setCreatedAt(deliveryPartner.getCreatedAt().toString());
        deliveryPartnerResponse.setCreatedBy(deliveryPartner.getCreatedBy());
        deliveryPartnerResponse.setUpdatedAt(deliveryPartner.getUpdatedAt() != null ? deliveryPartner.getUpdatedAt().toString() : null);
        deliveryPartnerResponse.setUpdatedBy(deliveryPartner.getUpdatedBy());

        return deliveryPartnerResponse;
    }

}
