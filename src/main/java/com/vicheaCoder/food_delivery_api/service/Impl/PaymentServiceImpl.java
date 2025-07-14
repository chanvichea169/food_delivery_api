package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.dto.PaymentRequest;
import com.vicheaCoder.food_delivery_api.service.PaymentService;
import com.vicheaCoder.food_delivery_api.service.handler.PaymentHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentHandlerService paymentHandlerService;

    public PaymentServiceImpl(PaymentHandlerService paymentHandlerService) {
        this.paymentHandlerService = paymentHandlerService;
    }

    @Override
    public String pay(PaymentRequest paymentRequest) {
        log.info("Processing payment request: {}", paymentRequest);
        try {
            return paymentHandlerService.postingToPaymentGateway(paymentRequest);
        } catch (Exception e) {
            log.error("Error processing payment: {}", e.getMessage());
            return "Payment processing failed: " + e.getMessage();
        }
    }

    @Override
    public String inquiry(String orderId) {
        // Implement inquiry logic here
        return "Inquiry successful for order ID: " + orderId;
    }
}
