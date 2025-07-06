package com.duckstar.repository.AnimeRepository;

import com.duckstar.web.dto.AnimeResponseDto;

public interface AnimeRepositoryCustom {
    AnimeResponseDto.AnimeHomeDto getAnimeHomeDtoById(Long animeId);
}
