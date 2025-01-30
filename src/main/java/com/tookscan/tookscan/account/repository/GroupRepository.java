package com.tookscan.tookscan.account.repository;

import com.tookscan.tookscan.account.domain.Group;

import java.util.List;

public interface GroupRepository {

    Group findByIdOrElseThrow(Long id);

    void save(Group group);

    boolean existsByName(String name);

    void deleteByIdOrElseThrow(Long id);

    List<Group> findByIds(List<Long> groupIds);

    List<Group> findAll();
}
