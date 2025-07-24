package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.WeekHandler;
import com.duckstar.domain.Anime;
import com.duckstar.domain.Week;
import com.duckstar.domain.mapping.anime.AnimeRecord;
import com.duckstar.repository.AnimeRecordRepository;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import com.duckstar.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeekService {

    private final WeekRepository weekRepository;
    private final AnimeRepository animeRepository;
    private final AnimeRecordRepository animeRecordRepository;

//    public Week getCurrentWeek() {
//        return weekRepository.findWeekTopByOrderByStartDateTimeDesc().orElseThrow(() ->
//                new WeekHandler(ErrorStatus.WEEK_NOT_FOUND));
//    }

//    public void saveUpcomingWeek() {
//        LocalDateTime startDateTime = LocalDateTime.now();
//        LocalDateTime endDateTime = startDateTime.plusDays(7);
//        Week upcomingWeek = Week.builder()
//                .startDateTime(startDateTime)
//                .endDateTime(endDateTime)
//                .year(startDateTime.getYear())
//                .quarter()
//                .weekNumber()
//                .isTwin()
//                .build();
//    }
}
