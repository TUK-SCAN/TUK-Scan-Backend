package com.tookscan.tookscan.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "price_policies")
public class PricePolicy {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "recovery_option_price", nullable = false)
    private String recoveryOptionPrice;

    @Column(name = "price_per_page", nullable = false)
    private Integer pricePerPage;

    @Column(name = "delivery_price", nullable = false)
    private Integer deliveryPrice;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public PricePolicy(String recoveryOptionPrice, Integer pricePerPage, Integer deliveryPrice, LocalDate startDate, LocalDate endDate) {
        this.recoveryOptionPrice = recoveryOptionPrice;
        this.pricePerPage = pricePerPage;
        this.deliveryPrice = deliveryPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
