package com.duckstar.domain.mapping.anime;

import com.duckstar.domain.Anime;
import com.duckstar.domain.Week;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AnimeRankWeekly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    // 득표율과 순위 정보
    // (받은 별 개수 / n주차 전체 별 개수) * 100
    private Float starPercent;

    @Column(name = "`rank`")
    private Integer rank;

    private Integer rankDiff;

    // 부가 정보
    private Float starAverage;

    private Integer star_0_5 = 0;
    private Integer star_1_0 = 0;
    private Integer star_1_5 = 0;
    private Integer star_2_0 = 0;
    private Integer star_2_5 = 0;
    private Integer star_3_0 = 0;
    private Integer star_3_5 = 0;
    private Integer star_4_0 = 0;
    private Integer star_4_5 = 0;
    private Integer star_5_0 = 0;
}
