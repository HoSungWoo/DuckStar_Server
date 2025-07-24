package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.TwinPolicy;
import com.duckstar.globalUtil.QuarterUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        indexes = {
                @Index(name = "idx_week_yqw", columnList = "year, quarter, week_number")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"year", "quarter", "week_number"})
        }
)
public class Week extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;    // 주간 시작일 (요일은 일요일)
    private LocalDate endDate;    // 투표 마감일 (start + 7일)

    private Integer year;       // 2025

    private Integer quarter;       // 3 분기

    private Integer weekNumber;     // 11 주차

    private Boolean isTwin;     // 분기가 바뀌는 주차는 쌍둥이 (예: 6월 5주차 & 7월 1주차)

    // 투표자 수
    private Integer animeVoteCount = 0;
    private Integer characterVoteCount = 0;

    // 스타 총 개수
    private Float animeStarTotal = 0f;
    private Float characterStarTotal = 0f;

    private Week(
            LocalDate startDate,
            LocalDate endDate,
            Integer year,
            Integer quarter,
            Integer weekNumber,
            Boolean isTwin
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.year = year;
        this.quarter = quarter;
        this.weekNumber = weekNumber;
        this.isTwin = isTwin;
    }

    //==생성 메서드==//
    public static Week create(LocalDate startDate, LocalDate endDate, TwinPolicy policy) {
        int year = startDate.getYear();
        int quarterOfStartDate = QuarterUtil.getQuarterValue(startDate);
        int weekNumber = QuarterUtil.calculateWeekNumber(startDate);

        boolean isTwin;
        if (policy == TwinPolicy.FORCE_TWIN) {
            isTwin = true;
        } else {
            int quarterOfEndDate = QuarterUtil.getQuarterValue(endDate);
            isTwin = (quarterOfStartDate != quarterOfEndDate);
        }

        return new Week(
                startDate,
                endDate,
                year,
                quarterOfStartDate,
                weekNumber,
                isTwin
        );
    }
}
