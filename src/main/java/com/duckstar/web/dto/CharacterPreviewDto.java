package com.duckstar.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CharacterPreviewDto {
    Long characterId;
    String nameKor;
    String nameEng;
    String nameKanji;
    String cv;

    String thumbnailUrl;

    @QueryProjection
    public CharacterPreviewDto(Long characterId, String nameKor, String nameEng, String nameKanji, String cv, String thumbnailUrl) {
        this.characterId = characterId;
        this.nameKor = nameKor;
        this.nameEng = nameEng;
        this.nameKanji = nameKanji;
        this.cv = cv;
        this.thumbnailUrl = thumbnailUrl;
    }
}
