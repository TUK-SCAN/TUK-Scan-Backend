package com.tookscan.tookscan.order.application.guest.service;

import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.address.domain.service.AddressService;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.guest.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.guest.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.guest.usecase.CreateOrderUseCase;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.service.DeliveryService;
import com.tookscan.tookscan.order.domain.service.DocumentService;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import com.tookscan.tookscan.order.repository.mysql.DeliveryRepository;
import com.tookscan.tookscan.order.repository.mysql.DocumentRepository;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import com.tookscan.tookscan.order.repository.mysql.PricePolicyRepository;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeService;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeHistoryRepository;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {
    private final DeliveryRepository deliveryRepository;
    private final PricePolicyRepository pricePolicyRepository;
    private final OrderRepository orderRepository;
    private final DocumentRepository documentRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final AuthenticationCodeHistoryRepository authenticationCodeHistoryRepository;

    private final OrderService orderService;
    private final AddressService addressService;
    private final DeliveryService deliveryService;
    private final DocumentService documentService;
    private final AuthenticationCodeService authenticationCodeService;

    @Override
    @Transactional
    public CreateOrderResponseDto execute(CreateOrderRequestDto requestDto) {

        // 해당 번호에 관련된 인증번호 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.deliveryInfo().phoneNumber())
                .orElse(null);

        // 인증번호 인증이 완료되었는지 확인
        authenticationCodeService.validateAuthenticationCode(authenticationCode);

        // 가격 정책 조회
        PricePolicy pricePolicy = pricePolicyRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        LocalDate.now(), LocalDate.now())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRICE_POLICY));

        // 주소 정보 생성
        Address address = addressService.createAddress(
                requestDto.deliveryInfo().address().addressName(),
                requestDto.deliveryInfo().address().region1DepthName(),
                requestDto.deliveryInfo().address().region2DepthName(),
                requestDto.deliveryInfo().address().region3DepthName(),
                requestDto.deliveryInfo().address().region4DepthName(),
                requestDto.deliveryInfo().address().addressDetail(),
                requestDto.deliveryInfo().address().latitude(),
                requestDto.deliveryInfo().address().longitude()
        );

        // 배송 정보 생성
        Delivery delivery = deliveryService.createDelivery(
                requestDto.deliveryInfo().receiverName(),
                requestDto.deliveryInfo().phoneNumber(),
                requestDto.deliveryInfo().email(),
                EDeliveryStatus.DELIVERY_READY,
                requestDto.deliveryInfo().request(),
                address,
                pricePolicy.getDeliveryPrice()
        );
        deliveryRepository.save(delivery);

        // 주문 생성
        Order order = orderService.createOrder(null, false, delivery, pricePolicy);
        orderRepository.save(order);

        // 문서 생성
        requestDto.documents().forEach(doc -> {
            Document document = documentService.createDocument(
                    doc.name(),
                    doc.request(),
                    doc.pagePrediction(),
                    doc.recoveryOption(),
                    order
            );
            documentRepository.save(document);
        });

        // 인증번호 삭제
        authenticationCodeRepository.deleteById(requestDto.deliveryInfo().phoneNumber());

        // 인증번호 발급 이력 삭제
        authenticationCodeHistoryRepository.deleteById(requestDto.deliveryInfo().phoneNumber());

        return CreateOrderResponseDto.of(order.getOrderNumber(), delivery.getReceiverName(), order.getDocumentsTotalAmount(), delivery.getEmail(), delivery.getAddress().getFullAddress());
    }
}
