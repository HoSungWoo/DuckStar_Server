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

    @Column(nullable = false)
    private LocalDate startDate;    // 투표 시작일 (요일은 일요일)

    @Column(nullable = false)
    private LocalDate endDate;    // 투표 마감일 (start + 1 week)

    @Column(nullable = false)
    private Integer year;       // 2025

    @Column(nullable = false)
    private Integer quarter;       // 3 분기

    @Column(nullable = false)
    private Integer weekNumber;     // 11 주차

    private Boolean isTwin;     // 분기가 바뀌는 주차는 쌍둥이 (예: 6월 5주차 & 7월 1주차)

    // 투표자 수
    private Integer animeVoteCount;
    private Integer characterVoteCount;

    // 스타 총 개수
    private Float animeStarTotal;
    private Float characterStarTotal;

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
    public static Week create(LocalDate startDate, TwinPolicy policy) {
        int year = startDate.getYear();
        int quarterOfStartDate = QuarterUtil.getQuarterValue(startDate);
        int weekNumber = QuarterUtil.calculateWeekNumber(startDate);
        LocalDate endDate = startDate.plusWeeks(1);

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
