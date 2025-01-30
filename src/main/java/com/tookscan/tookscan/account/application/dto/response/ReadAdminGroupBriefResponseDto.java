package com.tookscan.tookscan.account.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.account.domain.Group;
import com.tookscan.tookscan.core.dto.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReadAdminGroupBriefResponseDto extends SelfValidating<ReadAdminGroupBriefResponseDto> {

    @JsonProperty("group_infos")
    private final List<GroupInfoDto> groupInfos;

    @Builder
    public ReadAdminGroupBriefResponseDto(List<GroupInfoDto> groupInfos) {
        this.groupInfos = groupInfos;
    }

    @Getter
    public static class GroupInfoDto extends SelfValidating<GroupInfoDto> {
        @JsonProperty("id")
        @NotNull(message = "id는 null일 수 없습니다.")
        private final Long id;

        @JsonProperty("name")
        @NotNull(message = "name은 null일 수 없습니다.")
        private final String name;

        @Builder
        public GroupInfoDto(Long id, String name) {
            this.id = id;
            this.name = name;
            this.validateSelf();
        }

        public static GroupInfoDto fromEntity(Group group) {
            return GroupInfoDto.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .build();
        }
    }

    public static ReadAdminGroupBriefResponseDto fromEntities(List<Group> groups) {
        return ReadAdminGroupBriefResponseDto.builder()
                .groupInfos(groups.stream()
                        .map(GroupInfoDto::fromEntity)
                        .toList())
                .build();
    }
}
