package com.tookscan.tookscan.order.domain;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "order_number", nullable = false, unique = true)
    private Long orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private EOrderStatus orderStatus;

    @Column(name = "is_by_user", nullable = false)
    private boolean isByUser;

    @Column(name = "order_password", length = 320)
    private String orderPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* -------------------------------------------- */
    /* One To Many Mapping ------------------------ */
    /* -------------------------------------------- */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Order(Long orderNumber, EOrderStatus orderStatus, boolean isByUser, String orderPassword, User user) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.isByUser = isByUser;
        this.orderPassword = orderPassword;
        this.user = user;
    }
}
