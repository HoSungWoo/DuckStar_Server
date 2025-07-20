package com.duckstar.domain;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.apiPayload.exception.handler.CharacterHandler;
import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CharacterStar extends BaseEntity {

    @Id
    private Long id;
    @MapsId  // Character의 id와 동일
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ani_character_id", nullable = false)
    private Character character;

    // 별점 분포
    private Integer star_0_5;
    private Integer star_1_0;
    private Integer star_1_5;
    private Integer star_2_0;
    private Integer star_2_5;
    private Integer star_3_0;
    private Integer star_3_5;
    private Integer star_4_0;
    private Integer star_4_5;
    private Integer star_5_0;

    //==비즈니스 로직==//

    public void starInit() {
        star_0_5 = 0;
        star_1_0 = 0;
        star_1_5 = 0;
        star_2_0 = 0;
        star_2_5 = 0;
        star_3_0 = 0;
        star_3_5 = 0;
        star_4_0 = 0;
        star_4_5 = 0;
        star_5_0 = 0;
    }

    public void starCount(Integer starInt) {
        switch (starInt) {
            case 1 -> this.star_0_5 += 1;
            case 2 -> this.star_1_0 += 1;
            case 3 -> this.star_1_5 += 1;
            case 4 -> this.star_2_0 += 1;
            case 5 -> this.star_2_5 += 1;
            case 6 -> this.star_3_0 += 1;
            case 7 -> this.star_3_5 += 1;
            case 8 -> this.star_4_0 += 1;
            case 9 -> this.star_4_5 += 1;
            case 10 -> this.star_5_0 += 1;

            default -> throw new CharacterHandler(
                    ErrorStatus.CHARACTER_STAR_NOT_VALID);
        }
    }

    public int getTotalVotes() {
        return star_0_5 + star_1_0 + star_1_5 + star_2_0 + star_2_5 +
                star_3_0 + star_3_5 + star_4_0 + star_4_5 + star_5_0;
    }

    public double getTotalStars() {
        return star_0_5 * 0.5 + star_1_0 * 1.0 +
                star_1_5 * 1.5 + star_2_0 * 2.0 +
                star_2_5 * 2.5 + star_3_0 * 3.0 +
                star_3_5 * 3.5 + star_4_0 * 4.0 +
                star_4_5 * 4.5 + star_5_0 * 5.0;
    }

    public double getAverageStars() {
        int totalVotes = getTotalVotes();
        if (totalVotes == 0) return 0.0;
        return getTotalStars() / totalVotes;
    }
}
