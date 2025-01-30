package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserGroupJpaRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug FROM UserGroup ug WHERE ug.user.id IN :userIdList AND ug.group.id IN :groupIdList")
    Set<UserGroup> findAllByUserIdInAndGroupIdIn(@Param("userIdList") List<UUID> userIdList, @Param("groupIdList") List<Long> groupIdList);
}