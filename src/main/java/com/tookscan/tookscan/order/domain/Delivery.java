package com.tookscan.tookscan.order.domain;

import com.tookscan.tookscan.order.domain.type.EDeliveryStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "deliveries")
public class Delivery {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "receiver_name", length = 10, nullable = false)
    private String receiverName;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Column(name = "request", length = 50, nullable = false)
    private String request;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private EDeliveryStatus deliveryStatus;

    @Column(name = "tracking_number", length = 20, nullable = false)
    private String trackingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Delivery(String receiverName, String phoneNumber, String email, String request, EDeliveryStatus deliveryStatus, String trackingNumber, Order order) {
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.request = request;
        this.deliveryStatus = deliveryStatus;
        this.trackingNumber = trackingNumber;
        this.order = order;
    }
}
