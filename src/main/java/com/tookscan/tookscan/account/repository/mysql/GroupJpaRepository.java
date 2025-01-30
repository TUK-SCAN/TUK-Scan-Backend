package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupJpaRepository extends JpaRepository<Group, Long> {

    boolean existsByName(String name);

    @Query("SELECT g FROM Group g WHERE g.id IN :groupIds")
    List<Group> findGroupByIds(@Param("groupIds") List<Long> groupIds);
}
