package com.tookscan.tookscan.order.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.PageInfoDto;
import com.tookscan.tookscan.core.dto.SelfValidating;
import com.tookscan.tookscan.core.utility.DateTimeUtil;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import com.tookscan.tookscan.payment.domain.Payment;
import com.tookscan.tookscan.payment.domain.type.EEasyPaymentProvider;
import com.tookscan.tookscan.payment.domain.type.EPaymentMethod;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import org.springframework.data.domain.Page;

@Getter
public class ReadOrderOverviewResponseDto extends SelfValidating<ReadOrderOverviewResponseDto> {

    @JsonProperty("orders")
    @NotNull
    private List<OrderInfoDto> orders;

    @JsonProperty("page_info")
    @NotNull
    private PageInfoDto pageInfo;

    @Getter
    public static class OrderInfoDto extends SelfValidating<OrderInfoDto> {
        @JsonProperty("id")
        @NotNull
        private Long orderId;

        @JsonProperty("order_status")
        @NotNull
        private EOrderStatus orderStatus;

        @JsonProperty("document_description")
        @NotNull
        private String documentDescription;

        @JsonProperty("order_number")
        @NotNull
        private Long orderNumber;

        @JsonProperty("order_date")
        @NotNull
        private String orderDate;

        @JsonProperty("receiver_name")
        @NotNull
        private String receiverName;

        @JsonProperty("address")
        @NotNull
        private String address;

        @JsonProperty("payment_method")
        private EPaymentMethod paymentMethod;

        @JsonProperty("easy_payment_provider")
        private EEasyPaymentProvider easyPaymentProvider;

        @JsonProperty("payment_total")
        private Integer paymentTotal;

        @Builder
        public OrderInfoDto(Long orderId, EOrderStatus orderStatus, String documentDescription, Long orderNumber, String orderDate, String receiverName, String address, EPaymentMethod paymentMethod, EEasyPaymentProvider easyPaymentProvider, Integer paymentTotal) {
            this.orderId = orderId;
            this.orderStatus = orderStatus;
            this.documentDescription = documentDescription;
            this.orderNumber = orderNumber;
            this.orderDate = orderDate;
            this.receiverName = receiverName;
            this.address = address;
            this.paymentMethod = paymentMethod;
            this.easyPaymentProvider = easyPaymentProvider;
            this.paymentTotal = paymentTotal;
            this.validateSelf();
        }

        public static OrderInfoDto fromEntity(Order order) {
            Optional<Payment> payment = Optional.ofNullable(order.getPayment());

            EPaymentMethod paymentMethod = payment.map(Payment::getMethod).orElse(null);
            EEasyPaymentProvider easyPaymentProvider = payment.map(Payment::getEasyPaymentProvider).orElse(null);
            Integer paymentTotal = payment.map(Payment::getTotalAmount).orElse(order.getDocumentsTotalAmount());

            return OrderInfoDto.builder()
                    .orderId(order.getId())
                    .orderStatus(order.getOrderStatus().toDisplayString())
                    .documentDescription(order.getDocumentsDescription())
                    .orderNumber(order.getOrderNumber())
                    .orderDate(DateTimeUtil.convertLocalDateTimeToKORString(order.getCreatedAt()))
                    .receiverName(order.getDelivery().getReceiverName())
                    .address(order.getDelivery().getAddress().getFullAddress())
                    .paymentMethod(paymentMethod)
                    .easyPaymentProvider(easyPaymentProvider)
                    .paymentTotal(paymentTotal)
                    .build();
        }
    }

    @Builder
    public ReadOrderOverviewResponseDto(List<OrderInfoDto> orders, PageInfoDto pageInfo) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.validateSelf();
    }

    public static ReadOrderOverviewResponseDto fromEntity(Page<Order> orders) {
        return ReadOrderOverviewResponseDto.builder()
                .orders(orders.stream()
                        .map(OrderInfoDto::fromEntity)
                        .toList())
                .pageInfo(PageInfoDto.fromEntity(orders))
                .build();
    }

}