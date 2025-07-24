package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimeImg extends BaseEntity {

    @Id
    private Long id;
    @MapsId  // Anime 에 종속 관계
    @OneToOne   // 포함 관계, EAGER
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String thumbnailUrl;
}