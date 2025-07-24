package com.duckstar.converter;

import com.duckstar.domain.mapping.anime.AnimeRecord;
import com.duckstar.web.dto.AnimeResponseDto.AnimeVotePreviewDto;

import java.util.List;

public class AnimeConverter {

    public static List<AnimeVotePreviewDto> toAnimeVotePreviews(List<AnimeRecord> records) {
        return records.stream().map(record -> AnimeVotePreviewDto.builder()
                .animeRecordId(record.getId())
                .nameKor(record.getAnime().getNameKor())
                .thumbnailUrl(record.getAnime().getAnimeImg().getImageUrl())  //TODO 썸네일로 변경 필요
                .build())
                .toList();
    }
}
