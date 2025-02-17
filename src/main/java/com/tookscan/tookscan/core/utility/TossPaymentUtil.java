package com.tookscan.tookscan.core.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tookscan.tookscan.core.dto.PaymentDto;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class TossPaymentUtil {
    @Value("${toss.payments.secret-key}")
    private String tossSecretKey;

    @Value("${toss.payments.confirm-url}")
    private String tossConfirmApiUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public String getTossConfirmRequestUrl() {
        return tossConfirmApiUrl;
    }

    public HttpHeaders getTossConfirmRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + tossSecretKey);

        return headers;
    }

    public String createTossConfirmRequestBody(String paymentKey, String orderId, Integer amount) {
        JSONObject payload = new JSONObject();

        payload.put("paymentKey", paymentKey);
        payload.put("orderId", orderId);
        payload.put("amount", amount);

        return payload.toJSONString();
    }

    public PaymentDto mapToPaymentDto(JSONObject jsonObject) {
        try {
            return objectMapper.readValue(jsonObject.toString(), PaymentDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map to PaymentDto: " + e.getMessage(), e);
        }
    }


}
