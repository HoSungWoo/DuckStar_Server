package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.WeekHandler;
import com.duckstar.domain.Week;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import com.duckstar.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeekService {

    private final WeekRepository weekRepository;
    private final AnimeRepository animeRepository;

    public Week getCurrentWeek() {
        return weekRepository.findWeekTopByOrderByStartDateTimeDesc().orElseThrow(() ->
                new WeekHandler(ErrorStatus.WEEK_NOT_FOUND));
    }

}
