package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerRequest;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerResponse;
import com.vicheaCoder.food_delivery_api.enumeration.VehicleType;
import com.vicheaCoder.food_delivery_api.model.DeliveryPartner;
import com.vicheaCoder.food_delivery_api.respository.DeliveryPartnerRepository;
import com.vicheaCoder.food_delivery_api.respository.DeliveryRepository;
import com.vicheaCoder.food_delivery_api.service.DeliveryPartnerService;
import com.vicheaCoder.food_delivery_api.service.handler.DeliveryPartnerHandlerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.vicheaCoder.food_delivery_api.utils.DateTimeUtils.convertStringToDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final DeliveryPartnerHandlerService deliveryPartnerHandlerService;
    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartner(new DeliveryPartner(), deliveryPartnerRequest);

        log.info("Creating new delivery partner with username: {}", deliveryPartner);
        deliveryPartnerRepository.saveAndFlush(deliveryPartner);

        if (deliveryPartner.getId() != null) {
            log.info("Delivery partner created successfully with ID: {}", deliveryPartner.getId());
            return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
        }

        return null;
    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {
        Optional<DeliveryPartner> optionalPartner = deliveryPartnerRepository.findById(id);

        if (optionalPartner.isPresent()) {
            DeliveryPartner updateDeliveryPartner = deliveryPartnerHandlerService
                    .convertDeliveryPartnerRequestToDeliveryPartner(optionalPartner.get(), deliveryPartnerRequest);

            updateDeliveryPartner.setUpdatedAt(new Date());
            updateDeliveryPartner.setUpdatedBy(Constant.SYSTEM_USER);
            log.info("Updating delivery partner with ID: {}", id);
            deliveryPartnerRepository.saveAndFlush(updateDeliveryPartner);
            log.info("Delivery partner updated successfully with ID: {}", id);
            return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(updateDeliveryPartner);
        }

        throw new EntityNotFoundException("DeliveryPartner with ID " + id + " not found.");
    }


    @Override
    public DeliveryPartnerResponse getById(Long id) {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DeliveryPartner with ID " + id + " not found."));

        return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
    }

    @Override
    public void delete(Long id) {
        if(!deliveryPartnerRepository.existsById(id)) {
            throw new EntityNotFoundException("DeliveryPartner with ID " + id + " not found.");
        }
        deliveryPartnerRepository.deleteById(id);
        log.info("Delivery partner with ID: {} deleted successfully", id);
    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {
        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAll();
        if (deliveryPartners.isEmpty()) {
            log.info("No delivery partners found.");
            return List.of();
        }

        log.info("Found {} delivery partners", deliveryPartners.size());
        return deliveryPartners.stream()
                .map(deliveryPartnerHandlerService::convertDeliveryPartnerToDeliveryPartnerResponse)
                .toList();
    }
}
