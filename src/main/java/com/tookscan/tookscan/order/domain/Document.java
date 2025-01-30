package com.tookscan.tookscan.order.domain;

import com.tookscan.tookscan.core.dto.BaseEntity;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import com.tookscan.tookscan.order.domain.type.EScanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "documents")
@SQLDelete(sql = "UPDATE documents SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Document extends BaseEntity {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Column(name = "additional_price", nullable = false)
    private Integer additionalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "recovery_option", nullable = false)
    private ERecoveryOption recoveryOption;

    @Enumerated(EnumType.STRING)
    @Column(name = "scan_status", nullable = false)
    private EScanStatus scanStatus;

    /* -------------------------------------------- */
    /* Many to One Column ------------------------- */
    /* -------------------------------------------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_policy_id", nullable = false)
    private PricePolicy pricePolicy;

    /* -------------------------------------------- */
    /* One to One Column -------------------------- */
    /* -------------------------------------------- */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_id")
    private Pdf pdf;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Document(String name, int pageCount, ERecoveryOption recoveryOption, Order order, PricePolicy pricePolicy,
                    int additionalPrice, EScanStatus scanStatus) {
        this.name = name;
        this.pageCount = pageCount;
        this.recoveryOption = recoveryOption;
        this.order = order;
        this.pricePolicy = pricePolicy;
        this.additionalPrice = additionalPrice;
        this.scanStatus = scanStatus;
    }

    public int calculatePrice() {
        return pricePolicy.calculatePrice(pageCount, recoveryOption) + additionalPrice;
    }
}

