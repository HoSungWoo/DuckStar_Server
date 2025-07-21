package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.anime.AnimeRecordWeekly;
import com.duckstar.domain.mapping.character.CharacterRecordWeekly;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(name = "idx_start_date_time", columnList = "start_date_time")
        }
)
public class Week extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;    // 주간 시작시간 (일요일 22시~)

    private Integer year;       // 2025

    private Integer quarter;       // 3 분기

    private Integer weekNumber;     // 11 주차

    // 스타 총 개수
    private Float animeStarTotal;
    private Float characterStarTotal;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<IpVoteRecord> ipVoteRecords = new ArrayList<>();

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AnimeRecordWeekly> animeRecordWeeklies = new ArrayList<>();

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CharacterRecordWeekly> characterRecordWeeklies = new ArrayList<>();
}
