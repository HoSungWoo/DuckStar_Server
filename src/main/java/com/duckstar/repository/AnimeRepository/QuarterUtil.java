package com.duckstar.repository.AnimeRepository;

import java.time.LocalDate;

public class QuarterUtil {

    public static LocalDate getQuarterStart(LocalDate date) {
        int month = ((date.getMonthValue() - 1) / 3) * 3 + 1; // 1, 4, 7, 10
        return LocalDate.of(date.getYear(), month, 1);
    }

    public static  LocalDate getQuarterEnd(LocalDate date) {
        LocalDate start = getQuarterStart(date);
        return start.plusMonths(3).minusDays(1);
    }
}
