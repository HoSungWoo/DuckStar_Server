package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.repository.AnimeStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimeStarService {

    private final AnimeStarRepository animeStarRepository;

    public void voteAnime(Long animeId, int starInt) {
        animeStarRepository.findById(animeId).orElseThrow(() ->
                new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND));

        switch (starInt) {
            case 1 -> animeStarRepository.incrementStar_0_5(animeId);
            case 2 -> animeStarRepository.incrementStar_1_0(animeId);
            case 3 -> animeStarRepository.incrementStar_1_5(animeId);
            case 4 -> animeStarRepository.incrementStar_2_0(animeId);
            case 5 -> animeStarRepository.incrementStar_2_5(animeId);
            case 6 -> animeStarRepository.incrementStar_3_0(animeId);
            case 7 -> animeStarRepository.incrementStar_3_5(animeId);
            case 8 -> animeStarRepository.incrementStar_4_0(animeId);
            case 9 -> animeStarRepository.incrementStar_4_5(animeId);
            case 10 -> animeStarRepository.incrementStar_5_0(animeId);
            default -> throw new AnimeHandler(ErrorStatus.ANIME_STAR_NOT_VALID);
        }
    }
}
