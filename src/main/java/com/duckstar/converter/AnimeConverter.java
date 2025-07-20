package com.duckstar.converter;

import com.duckstar.domain.Anime;
import com.duckstar.web.dto.AnimeResponseDto.AnimeVotePreviewDto;

import java.util.List;

public class AnimeConverter {

    public static List<AnimeVotePreviewDto> toAnimeVotePreviews(List<Anime> animes) {
        return animes.stream().map(anime -> AnimeVotePreviewDto.builder()
                .animeId(anime.getId())
                .nameKor(anime.getNameKor())
                .thumbnailUrl(anime.getAnimeImg().getImageUrl())  //TODO 썸네일로 변경 필요
                .build())
                .toList();
    }
}
