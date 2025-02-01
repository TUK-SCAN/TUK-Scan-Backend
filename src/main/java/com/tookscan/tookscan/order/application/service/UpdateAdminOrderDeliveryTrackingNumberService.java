package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDeliveryTrackingNumberRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDeliveryTrackingNumberUseCase;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.service.DeliveryService;
import com.tookscan.tookscan.order.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminOrderDeliveryTrackingNumberService implements UpdateAdminOrderDeliveryTrackingNumberUseCase {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryService deliveryService;

    @Override
    @Transactional
    public void execute(Long deliveryId, UpdateAdminOrderDeliveryTrackingNumberRequestDto requestDto) {
        Delivery delivery = deliveryRepository.findByIdOrElseThrow(deliveryId);
        deliveryService.updateTrackingNumber(delivery, requestDto.trackingNumber());
    }
}
