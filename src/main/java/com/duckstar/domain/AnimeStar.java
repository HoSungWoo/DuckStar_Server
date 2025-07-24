package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimeStar extends BaseEntity {

    @Id
    private Long id;
    @MapsId  // Anime 에 종속 관계
    @OneToOne   // 포함 관계, EAGER
    @JoinColumn(name = "anime_id")
    private Anime anime;

    private Integer starTotal;

    private Integer totalVoters;
    private Float starAverage;

    private Integer star_1_0;
    private Integer star_2_0;
    private Integer star_3_0;
    private Integer star_4_0;
    private Integer star_5_0;
}