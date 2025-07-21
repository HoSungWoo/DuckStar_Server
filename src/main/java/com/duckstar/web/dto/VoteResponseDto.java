package com.duckstar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VoteResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteResultDto {
        String ipAddress;
        Integer votedItemsCount;
        Integer starTotal;
    }
}
