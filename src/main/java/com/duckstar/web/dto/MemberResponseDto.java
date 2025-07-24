package com.duckstar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDto {

    @Builder
    @Getter
    public static class MemberProfileDto {
        Long memberId;
        String name;
        String profileImgUrl;
    }
}
