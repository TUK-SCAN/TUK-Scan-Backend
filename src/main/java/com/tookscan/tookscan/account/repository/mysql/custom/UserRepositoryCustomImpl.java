package com.tookscan.tookscan.account.repository.mysql.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tookscan.tookscan.account.domain.QUser;
import com.tookscan.tookscan.account.domain.QUserGroup;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<UUID> findUserIdsByFilters(String searchType, String search, Long groupId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        QUser user = QUser.user;
        QUserGroup userGroup = QUserGroup.userGroup;

        BooleanExpression predicate = getSearchPredicate(user, searchType, search);
        if (predicate == null) {
            predicate = Expressions.asBoolean(true).isTrue();
        }

        if (startDate != null) {
            predicate = predicate.and(user.createdAt.goe(startDate.atStartOfDay()));
        }

        if (endDate != null) {
            predicate = predicate.and(user.createdAt.lt(endDate.plusDays(1).atStartOfDay()));
        }

        if (groupId != null) {
            predicate = predicate.and(user.userGroups.any().id.eq(groupId));
        }

        List<UUID> userIds = jpaQueryFactory
                .select(user.id)
                .from(user)
                .leftJoin(user.userGroups, userGroup)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .select(user.id)
                .from(user)
                .leftJoin(user.userGroups, userGroup)
                .where(predicate)
                .fetch()
                .size();

        return new PageImpl<>(userIds, pageable, total);
    }

    private BooleanExpression getSearchPredicate(QUser user, String filterColumn, String search) {

        if (filterColumn == null || search == null || search.isBlank()) {
            return null;
        }

        // 동적 조건 생성 함수 매핑
        Map<String, Function<String, BooleanExpression>> predicates = Map.of(
                "email", user.email::containsIgnoreCase,
                "name", user.name::containsIgnoreCase,
                "phone-number", user.phoneNumber::containsIgnoreCase,
                "serial-id", user.serialId::containsIgnoreCase,
                "memo", user.memo::containsIgnoreCase
        );

        // 검색 타입에 따른 Predicate 생성
        Function<String, BooleanExpression> predicateFunction = predicates.get(filterColumn);

        if (predicateFunction == null) {
            throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        }

        return predicateFunction.apply(search);
    }
}
