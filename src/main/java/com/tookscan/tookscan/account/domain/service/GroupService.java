package com.tookscan.tookscan.account.domain.service;

import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public Group createGroup(String name, boolean isExists) {
        if (isExists) {
            throw new CommonException(ErrorCode.ALREADY_EXIST_RESOURCE);
        }

        return Group.builder()
                .name(name)
                .build();
    }

    public Group updateGroupName(Group group, String name) {
        group.updateName(name);
        return group;
    }
}
