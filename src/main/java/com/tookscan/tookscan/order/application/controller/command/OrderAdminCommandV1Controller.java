package com.tookscan.tookscan.order.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.request.CreateAdminOrderMemoRequestDto;
import com.tookscan.tookscan.order.application.dto.request.DeleteAdminDocumentsRequestDto;
import com.tookscan.tookscan.order.application.dto.request.DeleteAdminOrdersRequestDto;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDeliveryRequestDto;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDeliveryTrackingNumberRequestDto;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDocumentsRequestDto;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrdersStatusRequestDto;
import com.tookscan.tookscan.order.application.usecase.CreateAdminOrderMemoUseCase;
import com.tookscan.tookscan.order.application.usecase.DeleteAdminDocumentsUseCase;
import com.tookscan.tookscan.order.application.usecase.DeleteAdminOrdersUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDeliveryTrackingNumberUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDeliveryUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDocumentsUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersDeliveriesTrackingNumberUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersStatusUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class OrderAdminCommandV1Controller {

    private final CreateAdminOrderMemoUseCase createAdminOrderMemoUseCase;
    private final UpdateAdminOrdersStatusUseCase updateAdminOrdersStatusUseCase;
    private final DeleteAdminOrdersUseCase deleteAdminOrdersUseCase;
    private final UpdateAdminOrderDeliveryUseCase updateAdminOrderDeliveryUseCase;
    private final UpdateAdminOrdersDeliveriesTrackingNumberUseCase updateAdminOrdersDeliveriesTrackingNumber;
    private final UpdateAdminOrderDeliveryTrackingNumberUseCase updateAdminOrderDeliveryTrackingNumberUseCase;
    private final DeleteAdminDocumentsUseCase deleteAdminDocumentsUseCase;
    private final UpdateAdminOrderDocumentsUseCase updateAdminOrderDocumentsUseCase;

    /**
     * 4.3.2 관리자 주문 상태 일괄 변경
     */
    @Operation(summary = "관리자 주문 상태 일괄 변경", description = "관리자가 여러 주문의 상태를 일괄 변경합니다.")
    @PostMapping(value = "/orders/status")
    public ResponseDto<Void> updateOrderStatus(
         @RequestBody @Valid UpdateAdminOrdersStatusRequestDto requestDto
    ) {
        updateAdminOrdersStatusUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 4.3.3 관리자 주문 메모 작성
     */
    @Operation(summary = "관리자 주문 메모 작성", description = "관리자가 주문에 메모를 작성합니다.")
    @PostMapping(value = "/orders/{orderId}/memo")
    public ResponseDto<Void> createOrderMemo(
            @PathVariable Long orderId,
            @RequestBody @Valid CreateAdminOrderMemoRequestDto requestDto
    ) {
        createAdminOrderMemoUseCase.execute(orderId, requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 4.3.4 관리자 배송지 정보 수정
     */
    @Operation(summary = "관리자 배송지 정보 수정", description = "관리자가 주문의 배송지 정보를 수정합니다.")
    @PutMapping(value = "/deliveries/{deliveryId}")
    public ResponseDto<Void> updateOrderAddress(
            @PathVariable Long deliveryId,
            @RequestBody @Valid UpdateAdminOrderDeliveryRequestDto requestDto
    ) {
        updateAdminOrderDeliveryUseCase.execute(deliveryId, requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 4.3.8 관리자 운송장 번호 일괄 등록
     */
    @Operation(summary = "관리자 운송장 번호 일괄 등록", description = "관리자가 여러 주문의 운송장 번호를 일괄 등록합니다.")
    @PostMapping(value = "/deliveries/tracking-number", consumes = "multipart/form-data")
    public ResponseDto<Void> updateOrderTrackingNumber(
            @RequestParam("file") MultipartFile file
    ) {
        updateAdminOrdersDeliveriesTrackingNumber.execute(file);
        return ResponseDto.ok(null);
    }
  
    /**
     * 4.3.7 관리자 운송장 번호 등록
     */
    @Operation(summary = "관리자 운송장 번호 등록", description = "관리자가 주문에 운송장 번호를 등록합니다.")
    @PatchMapping(value = "/deliveries/{deliveryId}/tracking-number")
    public ResponseDto<Void> updateOrderTrackingNumber(
            @PathVariable Long deliveryId,
            @RequestBody @Valid UpdateAdminOrderDeliveryTrackingNumberRequestDto requestDto
    ) {
        updateAdminOrderDeliveryTrackingNumberUseCase.execute(deliveryId, requestDto);
        return ResponseDto.ok(null);
    }
  
    /**
     * 4.4.1 관리자 주문 상세 상품 수정
     */
    @Operation(summary = "관리자 주문 상세 상품 수정", description = "관리자가 주문의 상세 상품을 수정합니다.")
    @PutMapping(value = "orders/{orderId}/documents")
    public ResponseDto<Void> updateOrderDocuments(
            @PathVariable Long orderId,
            @RequestBody @Valid UpdateAdminOrderDocumentsRequestDto requestDto
    ) {
        updateAdminOrderDocumentsUseCase.execute(orderId, requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 4.5.1 관리자 주문 일괄 삭제
     */
    @Operation(summary = "관리자 주문 일괄 삭제", description = "관리자가 여러 주문을 삭제합니다.")
    @DeleteMapping(value = "/orders")
    public ResponseDto<Void> deleteOrders(
            @RequestBody @Valid DeleteAdminOrdersRequestDto requestDto
    ) {
        deleteAdminOrdersUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 4.5.2 관리자 상품 일괄 삭제
     */
    @Operation(summary = "관리자 상품 일괄 삭제", description = "관리자가 여러 상품을 삭제합니다.")
    @DeleteMapping(value = "/documents")
    public ResponseDto<Void> deleteDocuments(
            @RequestBody @Valid DeleteAdminDocumentsRequestDto requestDto
    ) {
        deleteAdminDocumentsUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }
}
