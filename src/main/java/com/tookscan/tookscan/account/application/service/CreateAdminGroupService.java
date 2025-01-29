package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.request.CreateAdminGroupRequestDto;
import com.tookscan.tookscan.account.application.usecase.CreateAdminGroupUseCase;
import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.account.domain.service.GroupService;
import com.tookscan.tookscan.account.repository.mysql.GroupRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAdminGroupService implements CreateAdminGroupUseCase {

    private final GroupRepository groupRepository;

    private final GroupService groupService;

    @Override
    @Transactional
    public void execute(CreateAdminGroupRequestDto requestDto) {

        // 중복 그룹명 체크
        if (groupRepository.existsByName(requestDto.name())) {
            throw new CommonException(ErrorCode.ALREADY_EXIST_RESOURCE);
        }

        // 그룹 생성
        Group group = groupService.createGroup(requestDto.name());

        groupRepository.save(group);
    }
}
