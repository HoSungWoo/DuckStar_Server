package com.duckstar.domain.mapping.anime;

import com.duckstar.domain.Anime;
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
     * 받은 별 개수는 아래가 모두 반영된 것
     *  - 트렌드(투표자 수), 정성 평가(평균 별점)
     */
    private Integer starTotal;

    @Column(name = "`rank`")
    private Integer rank;

    private Integer rankDiff;

    // 평균 별점
    private Float starAverage;

    private Integer star_1_0;
    private Integer star_2_0;
    private Integer star_3_0;
    private Integer star_4_0;
    private Integer star_5_0;
}