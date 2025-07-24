package com.duckstar.service;

import com.duckstar.domain.Week;
import com.duckstar.domain.mapping.anime.AnimeRecord;
import com.duckstar.repository.AnimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRecordRepository animeRecordRepository;
    private final WeekService weekService;

//    public List<AnimeRecord> getUpcomingAnimes() {
//        Week currentWeek = weekService.getCurrentWeek();
//        Long weekId = currentWeek.getId();
//        return animeRecordRepository.findAllByWeek_IdOrderByAnime_NameKorAsc(weekId);
//    }
}
