package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.address.domain.service.AddressService;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.request.CreateOrderRequestDto.RequestDocument;
import com.tookscan.tookscan.order.application.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateOrderUseCase;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.service.DeliveryService;
import com.tookscan.tookscan.order.domain.service.DocumentService;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.domain.service.PricePolicyService;
import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import com.tookscan.tookscan.order.repository.mysql.DeliveryRepository;
import com.tookscan.tookscan.order.repository.mysql.DocumentRepository;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final UserRepository userRepository;
    private final OrderService orderService;
    private final DocumentService documentService;
    private final AddressService addressService;
    private final DeliveryService deliveryService;
    private final PricePolicyService pricePolicyService;
    private final OrderRepository orderRepository;
    private final DocumentRepository documentRepository;
    private final DeliveryRepository deliveryRepository;


    @Override
    @Transactional
    public CreateOrderResponseDto execute(UUID accountId, CreateOrderRequestDto requestDto) {
        // 계정 조회
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 주문번호 생성
        Long orderNumber = orderService.createOrderNumber();

        // 주문 생성
        Order order = orderService.createOrder(user, orderNumber, true, null);
        orderRepository.save(order);

        // 문서 생성
        int paymentPredition = 0;

        for (RequestDocument doc : requestDto.documents()) {
            Document document = documentService.createDocument(
                    doc.name(),
                    doc.request(),
                    doc.pagePrediction(),
                    doc.recoveryOption(),
                    order
            );
            documentRepository.save(document);

            paymentPredition += pricePolicyService.calculatePrice(doc.pagePrediction(), doc.recoveryOption());
        }

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
                null,
                requestDto.deliveryInfo().request(),
                address,
                order
        );
        deliveryRepository.save(delivery);


        return CreateOrderResponseDto.of(orderNumber, delivery.getReceiverName(), paymentPredition, delivery.getEmail(), address.getFullAddress());
    }
}
