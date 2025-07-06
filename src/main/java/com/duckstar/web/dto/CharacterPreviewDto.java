package com.duckstar.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CharacterPreviewDto {
    Long characterId;
    String nameKor;
    String nameEng;
    String nameKanji;
    String cv;

    String imageUrl;

    @QueryProjection
    public CharacterPreviewDto(Long characterId, String nameKor, String nameEng, String nameKanji, String cv, String imageUrl) {
        this.characterId = characterId;
        this.nameKor = nameKor;
        this.nameEng = nameEng;
        this.nameKanji = nameKanji;
        this.cv = cv;
        this.imageUrl = imageUrl;
    }
}
