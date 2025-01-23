package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    public Delivery createDelivery(
            String receiverName,
            String phoneNumber,
            String email,
            EDeliveryStatus deliveryStatus,
            String request,
            Address address,
            Integer deliveryPrice
    ) {
        return Delivery.builder()
                .receiverName(receiverName)
                .phoneNumber(phoneNumber)
                .email(email)
                .request(request)
                .deliveryStatus(deliveryStatus)
                .address(address)
                .deliveryPrice(deliveryPrice)
                .build();
    }
}
