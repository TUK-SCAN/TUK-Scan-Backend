package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.UserRepository;
import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.address.domain.service.AddressService;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.request.CreateUserOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateUserOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateUserOrderUseCase;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.InitialDocument;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.service.DeliveryService;
import com.tookscan.tookscan.order.domain.service.DocumentService;
import com.tookscan.tookscan.order.domain.service.InitialDocumentService;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import com.tookscan.tookscan.order.repository.mysql.DeliveryRepository;
import com.tookscan.tookscan.order.repository.mysql.DocumentRepository;
import com.tookscan.tookscan.order.repository.mysql.InitialDocumentRepository;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import com.tookscan.tookscan.order.repository.mysql.PricePolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserOrderService implements CreateUserOrderUseCase {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DocumentRepository documentRepository;
    private final InitialDocumentRepository initialDocumentRepository;
    private final DeliveryRepository deliveryRepository;
    private final PricePolicyRepository pricePolicyRepository;

    private final OrderService orderService;
    private final DocumentService documentService;
    private final InitialDocumentService initialDocumentService;
    private final AddressService addressService;
    private final DeliveryService deliveryService;

    @Override
    @Transactional
    public CreateUserOrderResponseDto execute(UUID accountId, CreateUserOrderRequestDto requestDto) {
        // 계정 조회
        User user = userRepository.findByIdOrElseThrow(accountId);

        // 가격 정책 조회
        PricePolicy pricePolicy = pricePolicyRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate.now(), LocalDate.now())
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
        Order order = orderService.createOrder(user, true, delivery);
        orderRepository.save(order);

        // 문서 생성
        requestDto.documents().forEach(doc -> {
                    Document document = documentService.createDocument(
                            doc.name(),
                            doc.pagePrediction(),
                            doc.recoveryOption(),
                            order,
                            pricePolicy
                    );
                    order.getDocuments().add(document);
                    documentRepository.save(document);
        });

        requestDto.documents().forEach(doc -> {
            InitialDocument initialDocument = initialDocumentService.createInitialDocument(
                    doc.name(),
                    doc.pagePrediction(),
                    doc.recoveryOption(),
                    order,
                    pricePolicy
            );
            initialDocumentRepository.save(initialDocument);
        });

        return CreateUserOrderResponseDto.of(order.getOrderNumber(), delivery.getReceiverName(), order.getDocumentsTotalAmount(), delivery.getEmail(), delivery.getAddress().getFullAddress());
    }

}
