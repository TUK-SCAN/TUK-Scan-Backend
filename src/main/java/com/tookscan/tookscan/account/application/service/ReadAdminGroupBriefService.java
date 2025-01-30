package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadAdminGroupBriefResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAdminGroupBriefUseCase;
import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.account.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadAdminGroupBriefService implements ReadAdminGroupBriefUseCase {

    private final GroupRepository groupRepository;

    public ReadAdminGroupBriefResponseDto execute() {
        List<Group> groups = groupRepository.findAll();

        return ReadAdminGroupBriefResponseDto.fromEntities(groups);
    }
}
