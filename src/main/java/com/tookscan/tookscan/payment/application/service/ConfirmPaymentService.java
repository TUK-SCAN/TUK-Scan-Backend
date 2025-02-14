package com.tookscan.tookscan.payment.application.service;

import com.tookscan.tookscan.payment.application.dto.request.ConfirmPaymentRequestDto;
import com.tookscan.tookscan.payment.application.dto.response.ConfirmPaymentResponseDto;
import com.tookscan.tookscan.payment.application.usecase.ConfirmPaymentUseCase;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConfirmPaymentService implements ConfirmPaymentUseCase {
    private final PaymentRepository paymentRepository;

    // Toss Payments 관련 설정
    @Value("${toss.payments.secret-key}")
    private String tossSecretKey;

    // 결제 확인용 API URL (예시: 결제 생성 시 사용한 URL과 다를 수 있음)
    @Value("${toss.payments.confirm-url}")
    private String tossConfirmApiUrl;

    @Override
    public ConfirmPaymentResponseDto execute(ConfirmPaymentRequestDto requestDto) {
        // 1. Payment 엔티티 조회 (orderId 기준으로 조회)
        Payment payment = paymentRepository.findByOrderNumberOrElseThrow(requestDto.orderId());

        // 2. 결제 승인 요청 페이로드 구성
        // 공식문서에 따라 paymentKey, orderId, amount가 필요함
        Map<String, Object> payload = new HashMap<>();
        payload.put("paymentKey", requestDto.paymentKey());
        payload.put("orderId", requestDto.orderId());
        payload.put("amount", requestDto.amount());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + tossSecretKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(tossConfirmApiUrl, entity, Map.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
            throw new RuntimeException("Toss Payments 결제 승인 실패");
        }
        Map<String, Object> responseBody = responseEntity.getBody();

        // 3. 승인 결과 확인 (공식문서에 따르면 승인 성공 시 status 값이 "DONE"임)
        String status = (String) responseBody.get("status");
        if (!"DONE".equals(status)) {
            throw new RuntimeException("Payment confirmation unsuccessful: " + status);
        }

        // 3. Payment 엔티티 업데이트 (결제 승인 처리)
        payment.updateStatus(EPaymentStatus.DONE);
        payment.updateApprovedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        // 4. 응답 DTO 생성 및 반환
        return ConfirmPaymentResponseDto.of(
                payment.getOrder().getOrderNumber(),
                payment.getStatus().name(),
                "Payment confirmed successfully"
        );
    }
}
