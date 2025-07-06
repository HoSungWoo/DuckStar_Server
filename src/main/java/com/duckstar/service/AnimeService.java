package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.Anime;
import com.duckstar.domain.AnimeImg;
import com.duckstar.domain.AnimeStar;
import com.duckstar.repository.AnimeRepository;
import com.duckstar.web.dto.AnimeResponseDto;
import com.duckstar.web.dto.AnimeResponseDto.StarDistributeDto;
import com.duckstar.web.dto.CharacterResponseDto;
import com.duckstar.web.dto.CharacterResponseDto.CharacterPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRepository animeRepository;
//    private final CharacterService characterService;

    public AnimeResponseDto.AnimeHomeDto getAnimeHomeDto(Long animeId) {

        Anime anime = animeRepository.findById(animeId).orElseThrow(() ->
                new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND));

        List<String> imageUrls = anime.getAnimeImgs().stream()
                .map(AnimeImg::getImageUrl)
                .toList();

        StarDistributeDto starDistributeDto = getStarDistribute(anime);

//        List<CharacterPreviewDto> characterPreviewDtos =
//                characterService.getCharacterPreviewDtos(anime);

        return AnimeResponseDto.AnimeHomeDto.builder()
                .medium(anime.getMedium())
                .nameKor(anime.getNameKor())
                .nameOrigin(anime.getNameOrigin())
                .genre(anime.getGenre())
                .corp(anime.getCorp())
                .author(anime.getAuthor())
                .director(anime.getDirector())
                .airDate(anime.getAirDate())
                .airTime(anime.getAirTime())
                .dayOfWeek(anime.getDayOfWeek())
                .officalSite(anime.getOfficalSite())
                .otts(anime.getOtts())
                .minAge(anime.getMinAge())

                .imageUrls(imageUrls)
                .starDistributeDto(starDistributeDto)
                .characterPreviewDtos(Collections.emptyList())  // TODO
                .aniCommentPreviewDtos(Collections.emptyList()) // TODO
                .build();
    }

    private StarDistributeDto getStarDistribute(Anime anime) {
        AnimeStar animeStar = anime.getAnimeStar();
        return StarDistributeDto.builder()
                .star_0_5(animeStar.getStar_0_5())
                .star_1_0(animeStar.getStar_1_0())
                .star_1_5(animeStar.getStar_1_5())
                .star_2_0(animeStar.getStar_2_0())
                .star_2_5(animeStar.getStar_2_5())
                .star_3_0(animeStar.getStar_3_0())
                .star_3_5(animeStar.getStar_3_5())
                .star_4_0(animeStar.getStar_4_0())
                .star_4_5(animeStar.getStar_4_5())
                .star_5_0(animeStar.getStar_5_0())
                .build();
    }
}
