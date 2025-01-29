package com.tookscan.tookscan.account.domain.service;

import com.tookscan.tookscan.account.domain.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public Group createGroup(String name) {
        return Group.builder()
                .name(name)
                .build();
    }
}
