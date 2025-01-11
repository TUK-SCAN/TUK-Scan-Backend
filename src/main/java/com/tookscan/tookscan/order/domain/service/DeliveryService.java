package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    public Delivery createDelivery(
            String receiverName,
            String phoneNumber,
            String email,
            EDeliveryStatus deliveryStatus,
            String trackingNumber,
            String request,
            Address address,
            Order order
    ) {
        return Delivery.builder()
                .receiverName(receiverName)
                .phoneNumber(phoneNumber)
                .email(email)
                .request(request)
                .deliveryStatus(EDeliveryStatus.DELIVERY_READY)
                .trackingNumber(null)
                .address(address)
                .order(order)
                .build();
    }
}
