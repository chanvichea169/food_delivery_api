package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KhQRHandlerService {
    final private RestTemplate restTemplate;
    public KhQRHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String postToKhQRApi(PaymentRequest paymentRequest) {
        try {
            final String url = "https://api.khqr.com/payment";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer" + "your_api_key_here");

            HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);

            log.info("Posted to KhQR API with request: {}", paymentRequest);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            log.info("Response from KhQR API: {}", response.getBody());
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Payment request sent to KhQR API successfully.");
                return response.getBody();
            } else {
                log.error("Failed to send payment request to KhQR API. Status code: {}", response.getStatusCode());
                throw new RuntimeException("Failed to send payment request to KhQR API. Status code: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            log.error("Error occurred while posting to KhQR API: {}", ex.getMessage());
            throw new RuntimeException("Error occurred while posting to KhQR API: " + ex.getMessage(), ex);
        }
    }
}
