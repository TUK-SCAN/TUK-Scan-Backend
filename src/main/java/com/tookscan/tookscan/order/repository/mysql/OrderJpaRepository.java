package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.order.domain.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o " +
            "JOIN o.documents d " +
            "WHERE o.user = :user " +
            "AND (o.orderNumber LIKE %:search% " +
            "OR d.name LIKE %:search%)")
    Page<Order> findAllByUserAndSearch(@Param("user") User user, @Param("search") String search, Pageable pageable);

    Optional<Order> findByOrderNumber(String orderNumber);

    @Query("SELECT DISTINCT o FROM Order o " +
            "JOIN FETCH o.documents d " +
            "WHERE o.user.id IN :userIds")
    List<Order> findAllByUserIds(@Param("userIds") List<UUID> userIds);

    @Query("SELECT DISTINCT o FROM Order o " +
            "LEFT JOIN FETCH o.documents d " +
            "LEFT JOIN FETCH d.pricePolicy p " +
            "LEFT JOIN FETCH o.delivery del " +
            "LEFT JOIN FETCH o.user u " +
            "WHERE o.id IN :ids")
    List<Order> findAllWithDocumentsByIdIn(@Param("ids") List<Long> ids);
}
