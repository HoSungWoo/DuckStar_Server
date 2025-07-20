package com.duckstar.web.dto;

import com.duckstar.domain.enums.DayOfWeekShort;
import com.duckstar.domain.enums.Medium;
import com.duckstar.web.dto.AniCommentResponseDto.AniCommentPreviewDto;
import com.duckstar.web.dto.CharacterResponseDto.CharacterPreviewDto;
import com.duckstar.web.dto.StarInfoDto.StarDistributeDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AnimeResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimeRankDto {
        Long animeId;
        String nameKor;  // 한글 제목
        String corp;    // 제작사
        String thumbnailUrl;  // 썸네일

        Integer rank;
        Integer rankDiff;
        Float starPercent;

        StarDistributeDto starDistributeDto;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimeVotePreviewDto {
        Long animeId;
        String nameKor;  // 한글 제목
        String thumbnailUrl;  // 썸네일
    }

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
        LocalDate airDate;     // 방영일
        String airTime;     // 방영 시간
        DayOfWeekShort dayOfWeek;   // 방영 요일
        Map<String, String> officalSite;     // 관련 사이트
        Map<String, String> otts;  // OTT
        String minAge;    // 시청 등급

        String imageUrl;

        StarDistributeDto starDistributeDto;

        List<CharacterPreviewDto> characterPreviewDtos;

        List<AniCommentPreviewDto> aniCommentPreviewDtos;
    }
}