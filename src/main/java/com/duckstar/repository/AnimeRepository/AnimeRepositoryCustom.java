package com.duckstar.repository.AnimeRepository;

import com.duckstar.domain.Anime;
import com.duckstar.web.dto.AnimeResponseDto;

import java.util.List;

public interface AnimeRepositoryCustom {
    AnimeResponseDto.AnimeHomeDto getAnimeHomeDtoById(Long animeId);
    List<Anime> getCurrentQuarterAnimes();
}
