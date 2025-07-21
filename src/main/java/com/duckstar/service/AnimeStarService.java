package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.Week;
import com.duckstar.repository.AnimeRecordWeeklyRepository;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import com.duckstar.repository.AnimeStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimeStarService {

    private final AnimeRecordWeeklyRepository animeRecordWeeklyRepository;

    public void voteAnime(Long animeRecordId, int starInt) {
        switch (starInt) {
            case 1 -> animeRecordWeeklyRepository.incrementStar_0_5(animeRecordId);
            case 2 -> animeRecordWeeklyRepository.incrementStar_1_0(animeRecordId);
            case 3 -> animeRecordWeeklyRepository.incrementStar_1_5(animeRecordId);
            case 4 -> animeRecordWeeklyRepository.incrementStar_2_0(animeRecordId);
            case 5 -> animeRecordWeeklyRepository.incrementStar_2_5(animeRecordId);
            case 6 -> animeRecordWeeklyRepository.incrementStar_3_0(animeRecordId);
            case 7 -> animeRecordWeeklyRepository.incrementStar_3_5(animeRecordId);
            case 8 -> animeRecordWeeklyRepository.incrementStar_4_0(animeRecordId);
            case 9 -> animeRecordWeeklyRepository.incrementStar_4_5(animeRecordId);
            case 10 -> animeRecordWeeklyRepository.incrementStar_5_0(animeRecordId);
            default -> throw new AnimeHandler(ErrorStatus.ANIME_STAR_NOT_VALID);
        }
    }
}
