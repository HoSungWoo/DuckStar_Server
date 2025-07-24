package com.duckstar.domain.mapping.character;

import com.duckstar.domain.Anime;
import com.duckstar.domain.Character;
import com.duckstar.domain.StarDistribution;
import com.duckstar.domain.Week;
import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.anime.AnimeRecord;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

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

    private CharacterRecord(
            Week week,
            Character character,
            Integer starTotal,
            Integer totalVoters,
            Float starAverage,
            StarDistribution distribution
    ) {
        this.week = week;
        this.character = character;
        this.starTotal = starTotal;
        this.totalVoters = totalVoters;
        this.starAverage = starAverage;
        this.distribution = distribution;
    }

    public static CharacterRecord create(
            Week week,
            Character character,
            Integer starTotal,
            Integer totalVoters,
            Float starAverage,
            StarDistribution distribution
    ) {
        return new CharacterRecord(
                week,
                character,
                starTotal,
                totalVoters,
                starAverage,
                distribution
        );
    }
}