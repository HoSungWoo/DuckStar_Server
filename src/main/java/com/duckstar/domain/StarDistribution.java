package com.duckstar.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StarDistribution {

    private Integer star_1_0 = 0;
    private Integer star_2_0 = 0;
    private Integer star_3_0 = 0;
    private Integer star_4_0 = 0;
    private Integer star_5_0 = 0;

    public StarDistribution(
            Integer star_1_0,
            Integer star_2_0,
            Integer star_3_0,
            Integer star_4_0,
            Integer star_5_0
    ) {
        this.star_1_0 = star_1_0;
        this.star_2_0 = star_2_0;
        this.star_3_0 = star_3_0;
        this.star_4_0 = star_4_0;
        this.star_5_0 = star_5_0;
    }

    public int getTotalCount() {
        return star_1_0 + star_2_0 + star_3_0 + star_4_0 + star_5_0;
    }

    public float calculateAverage() {
        int total = getTotalCount();
        if (total == 0) return 0f;
        return (star_1_0 * 1 + star_2_0 * 2 + star_3_0 * 3 + star_4_0 * 4 + star_5_0 * 5) /
                (float) total;
    }
}
