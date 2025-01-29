package com.tookscan.tookscan.account.repository;

import com.nimbusds.jose.util.Pair;
import com.tookscan.tookscan.account.domain.UserGroup;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserGroupRepository {

    Set<Pair<UUID,Long>> findNotDuplicatedUserGroupInUserIdsAndGroupIds(List<UUID> userIds, List<Long> groupIds);

    void saveAll(List<UserGroup> userGroups);
}
