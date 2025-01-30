package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.account.repository.GroupRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

    private final GroupJpaRepository groupJpaRepository;

    @Override
    public Group findByIdOrElseThrow(Long id) {
        return groupJpaRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
    }

    @Override
    public void save(Group group) {
        groupJpaRepository.save(group);
    }

    @Override
    public boolean existsByName(String name) {
        return groupJpaRepository.existsByName(name);
    }

    @Override
    public List<Group> findByIds(List<Long> groupIds) {
        return groupJpaRepository.findGroupByIds(groupIds);
    }

    @Override
    public List<Group> findAll() {
        return groupJpaRepository.findAll();
    }
}
