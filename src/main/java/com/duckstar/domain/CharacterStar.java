package com.duckstar.domain;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
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
}
