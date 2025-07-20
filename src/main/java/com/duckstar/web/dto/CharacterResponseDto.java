package com.duckstar.web.dto;

import com.duckstar.web.dto.StarInfoDto.StarDistributeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CharacterResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CharacterRankDto {
        Long characterId;
        String nameKor;  // 캐릭터 한글 이름
        String animeNameKor;  // 애니 한글 제목
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
    public static class CharacterPreviewDto {
        Long characterId;
        String nameKor;
        String nameEng;
        String nameKanji;
        String cv;

        String thumbnailUrl;
    }
}
