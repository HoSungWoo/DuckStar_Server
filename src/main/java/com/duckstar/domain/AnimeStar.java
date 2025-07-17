package com.duckstar.domain;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AnimeStar extends BaseEntity {

    @Id
    private Long id;
    @MapsId  // Anime의 id와 동일
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private Anime anime;

    // 별점 분포
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

    //==비즈니스 로직==//

    public void starCount(Float rating) {
        int switchRating = Math.round(rating * 2);
        switch (switchRating) {
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

            default -> throw new AnimeHandler(
                    ErrorStatus.ANIME_STAR_NOT_VALID);
        }
    }
}