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

    private Float starAverage;
    private Float starTotal;

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

}