package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerRequest;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerResponse;
import com.vicheaCoder.food_delivery_api.model.DeliveryPartner;

import java.util.List;

public interface DeliveryPartnerService {

    DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest);
    DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest);
    DeliveryPartnerResponse getById(Long id);
    void delete(Long id);
    List<DeliveryPartnerResponse> getAll();
}
