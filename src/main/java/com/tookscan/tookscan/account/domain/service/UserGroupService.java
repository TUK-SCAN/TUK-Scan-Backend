package com.tookscan.tookscan.account.domain.service;

import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.domain.UserGroup;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService {

    public UserGroup createUserGroup(User user, Group group) {
        return UserGroup.builder()
                .user(user)
                .group(group)
                .build();
    }
}
