package com.duckstar.web.dto;

import com.duckstar.domain.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CharacterResponseDto {

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

        String imageUrl;
    }
}
