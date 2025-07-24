package com.duckstar.globalUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class QuarterUtil {

    public static int getQuarterValue(LocalDate date) {
        LocalDate quarterStart = getStartDateOfQuarter(date);
        return quarterStart.getMonthValue() / 3 + 1;  // 예: 10 / 3 + 1 = 4
    }

    public static int calculateWeekNumber(LocalDate date) {
        LocalDate quarterStartDate = getStartDateOfQuarter(date);
        DayOfWeek dayOfWeek = quarterStartDate.getDayOfWeek();

        LocalDate firstSundayOfQuarter =
                quarterStartDate.plusDays(7 - dayOfWeek.getValue());

        boolean isFirstWeek = (date.isEqual(firstSundayOfQuarter) ||
                date.isBefore(firstSundayOfQuarter));
        if (isFirstWeek) return 1;

        long days = ChronoUnit.DAYS.between(firstSundayOfQuarter, date);
        return (int) (days / 7) + 2;  // 1-based 주차
    }

    public static LocalDate getStartDateOfQuarter(LocalDate date) {
        int monthValue = date.getMonthValue();
        int month = 0;
        switch (monthValue) {
            case 1, 2, 3 -> month = 1;
            case 4, 5, 6 -> month = 4;
            case 7, 8, 9 -> month = 7;
            case 10, 11, 12 -> month = 10;
        }
        return LocalDate.of(date.getYear(), month, 1);
    }

    public static LocalDate getEndDateOfQuarter(LocalDate date) {
        LocalDate start = getStartDateOfQuarter(date);
        return start.plusMonths(3).minusDays(1);
    }
}
