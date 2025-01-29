package com.tookscan.tookscan.account.repository;

import com.tookscan.tookscan.account.domain.Group;

public interface GroupRepository {

    Group findByIdOrElseThrow(Long id);

    void save(Group group);

    boolean existsByName(String name);
}
