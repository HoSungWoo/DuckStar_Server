package com.duckstar.domain.mapping.anime;

import com.duckstar.domain.Anime;
import com.duckstar.domain.StarDistribution;
import com.duckstar.domain.Week;
import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimeRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    /**
     * '받은 별 개수(starTotal)'
     *  = 트렌드(투표자 수, totalVoters) * 정성 평가(평균 별점, starAverage)
     */
    private Integer starTotal;

    private Integer totalVoters;

    private Float starAverage;

    @Embedded
    private StarDistribution distribution;

    @Column(name = "`rank`")
    private Integer rank;

    private Integer rankDiff;   // 분기 신작의 경우 null

    private AnimeRecord(
        Week week,
        Anime anime,
        Integer starTotal,
        Integer totalVoters,
        Float starAverage,
        StarDistribution distribution
    ) {
        this.week = week;
        this.anime = anime;
        this.starTotal = starTotal;
        this.totalVoters = totalVoters;
        this.starAverage = starAverage;
        this.distribution = distribution;
    }

    public static AnimeRecord create(
            Week week,
            Anime anime,
            Integer starTotal,
            Integer totalVoters,
            Float starAverage,
            StarDistribution distribution
    ) {
        return new AnimeRecord(
                week,
                anime,
                starTotal,
                totalVoters,
                starAverage,
                distribution
        );
    }
}