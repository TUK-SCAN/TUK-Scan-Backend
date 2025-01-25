package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByPhoneNumberAndName(String phoneNumber, String name);

    @Query("SELECT DISTINCT u.id as userId, u as User " +
            "FROM User u " +
            "LEFT JOIN u.userGroups ug " +
            "WHERE (:search IS NULL OR u.name LIKE %:search% OR u.email LIKE %:search% OR u.phoneNumber LIKE %:search%) " +
            "AND (:groupId IS NULL OR ug.group.id = :groupId) " +
            "AND (:startDate IS NULL OR u.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR u.createdAt <= :endDate)")
    Page<UserProjection> findUserProjectionsByFilters(
            @Param("search") String search,
            @Param("groupId") Long groupId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.userGroups " +
            "WHERE u.id IN :userIds")
    List<User> findUserByIdsWithDetails(@Param("userIds") List<UUID> userIds);

    interface UserProjection {
        UUID getUserId();
        String getName();
        String getPhoneNumber();
    }
}
