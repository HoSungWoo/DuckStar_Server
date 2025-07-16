package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.anime.AnimeRankWeekly;
import com.duckstar.domain.mapping.character.CharacterRankWeekly;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Week extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;       // 2025

    private Integer quarter;       // 3 분기

    private Integer weekNumber;     // 11 주차

    // 스타 총 개수
    private Integer animeStarsTotal;
    private Integer characterStarsTotal;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<IpVoteRecord> ipVoteRecords = new ArrayList<>();

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AnimeRankWeekly> animeRankWeeklies = new ArrayList<>();

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CharacterRankWeekly> characterRankWeeklies = new ArrayList<>();
}
