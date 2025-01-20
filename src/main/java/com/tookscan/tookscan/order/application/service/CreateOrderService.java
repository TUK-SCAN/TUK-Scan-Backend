package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.address.domain.service.AddressService;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateOrderUseCase;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DocumentRepository documentRepository;
    private final DeliveryRepository deliveryRepository;
    private final PricePolicyRepository pricePolicyRepository;

    private final OrderService orderService;
    private final DocumentService documentService;
    private final AddressService addressService;
    private final DeliveryService deliveryService;

    @Override
    @Transactional
    public CreateOrderResponseDto execute(UUID accountId, CreateOrderRequestDto requestDto) {
        // 계정 조회
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

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
                address
        );
        deliveryRepository.save(delivery);

        // 주문번호 생성
        Long count = getTodayOrderCount();
        Long orderNumber = orderService.createOrderNumber(count);

        // 주문 생성
        PricePolicy pricePolicy = pricePolicyRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate.now(), LocalDate.now())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRICE_POLICY));

        Order order = orderService.createOrder(user, orderNumber, true, null, delivery, pricePolicy);
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

        return CreateOrderResponseDto.of(orderNumber, delivery.getReceiverName(), order.getDocumentsTotalAmount(), delivery.getEmail(), delivery.getAddress().getFullAddress());
    }

    /**
     *  TODO: 동시성 문제 개선 필요
     *  현재 구현은 단일 스레드 환경에서는 문제가 없으나, 다중 스레드 환경에서는 동일한 주문 번호가 생성될 가능성이 있습니다.
     *  대안 1: Redis의 INCR 명령어를 사용하여 하루 동안 유일한 증가값을 생성.
     *    예: "order_number:{날짜}" 키를 Redis에서 관리하여 INCR 명령어로 증가된 값을 사용.
     *  대안 2: 분산 ID 생성기(Snowflake) 또는 UUID 기반의 유니크한 ID를 생성.
     *  추후 동시성 문제를 해결하기 위한 개선 작업이 필요합니다.
     */
    private long getTodayOrderCount() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return orderRepository.countByCreatedAtBetween(startOfDay, endOfDay).orElse(0L);
    }

}
