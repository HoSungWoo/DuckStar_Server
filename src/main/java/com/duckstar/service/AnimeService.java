package com.duckstar.service;

import com.duckstar.domain.Week;
import com.duckstar.domain.mapping.anime.AnimeRecordWeekly;
import com.duckstar.repository.AnimeRecordWeeklyRepository;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimeRecordWeeklyRepository animeRecordWeeklyRepository;
    private final WeekService weekService;

    public List<AnimeRecordWeekly> getCurrentRecords() {
        Week currentWeek = weekService.getCurrentWeek();
        Long weekId = currentWeek.getId();
        return animeRecordWeeklyRepository.findAllByWeekIdOrderByAnime_NameKorAsc(weekId);
    }
}
