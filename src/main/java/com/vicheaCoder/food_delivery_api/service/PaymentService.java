package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.PaymentRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface PaymentService {
    String pay(PaymentRequest paymentRequest);

    String inquiry(String orderId);
}
