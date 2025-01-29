package com.tookscan.tookscan.account.application.service;

import com.nimbusds.jose.util.Pair;
import com.tookscan.tookscan.account.application.dto.request.CreateAdminUserGroupRequestDto;
import com.tookscan.tookscan.account.application.usecase.CreateAdminUserGroupUseCase;
import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.domain.UserGroup;
import com.tookscan.tookscan.account.domain.service.UserGroupService;
import com.tookscan.tookscan.account.repository.UserGroupRepository;
import com.tookscan.tookscan.account.repository.UserRepository;
import com.tookscan.tookscan.account.repository.mysql.GroupJpaRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAdminUserGroupService implements CreateAdminUserGroupUseCase {

    private final UserGroupRepository userGroupRepository;
    private final GroupJpaRepository groupJpaRepository;
    private final UserRepository userRepository;

    private final UserGroupService userGroupService;

    @Override
    @Transactional
    public void execute(CreateAdminUserGroupRequestDto requestDto) {

        Set<Pair<UUID, Long>> objectPairs = userGroupRepository.findNotDuplicatedUserGroupInUserIdsAndGroupIds(requestDto.userIds(), requestDto.groupIds());

        List<UserGroup> userGroups = objectPairs.stream()
                .map(pair -> {
                    User user = userRepository.findByIdOrElseThrow(pair.getLeft());
                    Group group = groupJpaRepository.findById(pair.getRight()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

                    return userGroupService.createUserGroup(user, group);
                })
                .toList();

        userGroupRepository.saveAll(userGroups);
    }
}
