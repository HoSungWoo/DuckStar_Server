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

    @Embedded
    private StarDistribution distribution;

    /**
     * '받은 별 개수(starTotal)'
     *  = 트렌드(투표자 수, totalVoters) * 정성 평가(평균 별점, starAverage)
     */
    private Integer starTotal;

    private Integer totalVoters;

    private Float starAverage;

    @Column(name = "`rank`")
    private Integer rank;

    private Integer rankDiff;   // 분기 신작의 경우 null

    private AnimeRecord(
        Week week,
        Anime anime,
        StarDistribution distribution,
        Integer starTotal,
        Integer totalVoters,
        Float starAverage
    ) {
        this.week = week;
        this.anime = anime;
        this.distribution = distribution;
        this.starTotal = starTotal;
        this.totalVoters = totalVoters;
        this.starAverage = starAverage;
    }

    public static AnimeRecord create(
            Week week,
            Anime anime,
            StarDistribution distribution
    ) {
        float starAverage = distribution.calculateAverage();
        int totalVoters = distribution.getTotalCount();
        int starTotal = (int) (totalVoters * starAverage);

        return new AnimeRecord(
                week,
                anime,
                distribution,
                starTotal,
                totalVoters,
                starAverage
        );
    }
}