package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.PaymentRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface PaymentService {
    /**
     * Processes a payment request.
     *
     * @param paymentRequest the payment request containing order details and payment information
     * @return a confirmation message indicating the result of the payment processing
     */
    String pay(PaymentRequest paymentRequest);

    /**
     * Inquires about the status of a payment for a given order ID.
     *
     * @param orderId the ID of the order to inquire about
     * @return a message indicating the result of the inquiry
     */
    String inquiry(String orderId);
}
