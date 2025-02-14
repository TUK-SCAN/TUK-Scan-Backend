package com.tookscan.tookscan.payment.application.service;

import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.OrderRepository;
import com.tookscan.tookscan.payment.application.dto.request.CreatePaymentRequestDto;
import com.tookscan.tookscan.payment.application.dto.response.CreatePaymentResponseDto;
import com.tookscan.tookscan.payment.application.usecase.CreatePaymentUseCase;
import com.tookscan.tookscan.payment.domain.Payment;
import com.tookscan.tookscan.payment.domain.type.EPaymentStatus;
import com.tookscan.tookscan.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreatePaymentService implements CreatePaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // Toss Payments 관련 설정을 application.properties 등에서 주입
    @Value("${toss.payments.secret-key}")
    private String tossSecretKey;

    @Value("${toss.payments.url}")
    private String tossApiUrl;

    @Override
    public CreatePaymentResponseDto execute(CreatePaymentRequestDto requestDto) {

        Order order = orderRepository.findByIdWithDocumentsOrElseThrow(requestDto.orderId());

        // 1. 주문번호 생성
        String orderId = order.getOrderNumber();

        // 2. Toss Payments API 요청 페이로드 구성
        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        payload.put("orderName", order.getDocumentsDescription());
        payload.put("amount", order.getTotalAmount());
        // 필요시 고객 정보, 기타 옵션 등 추가

        // 3. HTTP 헤더 구성 (Content-Type, 인증 헤더 등)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Basic 인증 방식: "API_KEY:" 문자열을 Base64로 인코딩하여 사용
        String auth = "Basic " + Base64.getEncoder()
                .encodeToString((tossSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", auth);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        // 4. Toss Payments API 호출 (RestTemplate 사용)
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> tossResponse = restTemplate.postForEntity(tossApiUrl, entity, Map.class);
        if (!tossResponse.getStatusCode().is2xxSuccessful() || tossResponse.getBody() == null) {
            throw new RuntimeException("Toss Payments 결제 주문 생성 실패");
        }
        Map<String, Object> responseBody = tossResponse.getBody();

        // 5. Toss Payments 응답에서 paymentKey와 결제창 URL 추출
        String paymentKey = (String) responseBody.get("paymentKey");
        Map<String, Object> checkout = (Map<String, Object>) responseBody.get("checkout");
        String paymentUrl = checkout != null ? (String) checkout.get("url") : null;

        // 5. Payment 엔티티 생성 및 DB 저장
        Payment payment = Payment.builder()
                .paymentKey(paymentKey) // 필요에 따라 Toss Payments의 paymentKey 등 실제 값을 저장
                .type("TOSS")
                .method(null)
                .totalAmount(order.getTotalAmount())
                .status(EPaymentStatus.READY)
                .requestedAt(LocalDateTime.now())
                .approvedAt(null)
                .easyPaymentProvider(null)
                .order(order)
                .build();
        paymentRepository.save(payment);

        return CreatePaymentResponseDto.of(orderId, paymentUrl);

    }
}
