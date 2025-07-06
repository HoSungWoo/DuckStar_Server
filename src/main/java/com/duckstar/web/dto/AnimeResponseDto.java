package com.duckstar.web.dto;

import com.duckstar.domain.enums.DayOfWeekShort;
import com.duckstar.domain.enums.Medium;
import com.duckstar.web.dto.AniCommentResponseDto.AniCommentPreviewDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Map;

import static com.duckstar.web.dto.CharacterResponseDto.*;

public class AnimeResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimeHomeDto {
        Medium medium;  // 분류 (TVA/MOVIE)
        String nameKor;  // 한글 제목
        String nameOrigin; // 원제
        String genre;   // 장르
        String corp;    // 제작사
        String author;  // 원작
        String director;    // 감독
        String airDate;     // 방영일
        String airTime;     // 방영 시간
        DayOfWeekShort dayOfWeek;   // 방영 요일
        Map<String, String> officalSite;     // 관련 사이트
        Map<String, String> otts;  // OTT
        String minAge;    // 시청 등급

        @Schema(description = "이미지 여러 개 둘 것인지 논의 필요함. 일단은 리스트")
        List<String> imageUrls;

        StarDistributeDto starDistributeDto;

        List<CharacterPreviewDto> characterPreviewDtos;

        List<AniCommentPreviewDto> aniCommentPreviewDtos;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StarDistributeDto {
        Integer star_0_5;
        Integer star_1_0;
        Integer star_1_5;
        Integer star_2_0;
        Integer star_2_5;
        Integer star_3_0;
        Integer star_3_5;
        Integer star_4_0;
        Integer star_4_5;
        Integer star_5_0;
    }
}