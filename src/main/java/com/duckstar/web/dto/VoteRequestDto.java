package com.duckstar.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.List;

public class VoteRequestDto {

    @Getter
    public static class AnimeStarDtoList {
        Long memberId;  // 로그인 멤버 id
        List<AnimeStarDto> animeStarList;
    }

    @Getter
    public static class CharacterStarDtoList {
        Long memberId;  // 로그인 멤버 id
        List<CharacterStarDto> characterStarList;
    }

    @Getter
    public static class AnimeStarDto {
        Long animeRecordId;

        @Max(value = 10, message = "별점은 최대 10까지입니다.")
        @Min(value = 1, message = "별점은 최소 1부터입니다.")
        Integer starInt;
    }

    @Getter
    public static class CharacterStarDto {
        Long characterRecordId;

        @Max(value = 10, message = "별점은 최대 10까지입니다.")
        @Min(value = 1, message = "별점은 최소 1부터입니다.")
        Integer starInt;
    }
}
