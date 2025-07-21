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
public class AnimeRecordWeekly {

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
     * 득표율 %
     * (받은 별 개수 / n주차 전체 별 개수) * 100
     */
    private Integer starPercent;

    @Column(name = "`rank`")
    private Integer rank;

    private Integer rankDiff;

    /**
     * 부가 정보
     */
    private Float starAverage;

    private Float starTotal;

    @Builder.Default private Integer star_0_5 = 0;
    @Builder.Default private Integer star_1_0 = 0;
    @Builder.Default private Integer star_1_5 = 0;
    @Builder.Default private Integer star_2_0 = 0;
    @Builder.Default private Integer star_2_5 = 0;
    @Builder.Default private Integer star_3_0 = 0;
    @Builder.Default private Integer star_3_5 = 0;
    @Builder.Default private Integer star_4_0 = 0;
    @Builder.Default private Integer star_4_5 = 0;
    @Builder.Default private Integer star_5_0 = 0;

    public int getTotalVotes() {
        return star_0_5 + star_1_0 + star_1_5 + star_2_0 + star_2_5 +
                star_3_0 + star_3_5 + star_4_0 + star_4_5 + star_5_0;
    }

    public float getTotalStars() {
        return (float) (star_0_5 * 0.5 + star_1_0 * 1.0 +
                star_1_5 * 1.5 + star_2_0 * 2.0 +
                star_2_5 * 2.5 + star_3_0 * 3.0 +
                star_3_5 * 3.5 + star_4_0 * 4.0 +
                star_4_5 * 4.5 + star_5_0 * 5.0);
    }

    public void updateTotalStarsAndStarPercent(Float animeStarTotal) {
        starTotal = getTotalStars();
        starPercent = (int) ((starTotal / animeStarTotal) * 100);
    }

    public void updateAverageStars() {
        int totalVotes = getTotalVotes();
        if (totalVotes == 0) {
            starAverage = null;
        } else {
            starAverage = getTotalStars() / totalVotes;
        }
    }
}
