package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.DayOfWeekShort;
import com.duckstar.domain.enums.Medium;
import com.duckstar.domain.mapping.anime.AnimeComment;
import com.duckstar.domain.mapping.anime.AnimeVoteWeekly;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Anime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Medium medium;      // 분류 (TVA/MOVIE)

    private String nameKor;    // 한글 제목

    private String nameOrigin; // 원제

    private String genre;   // 장르

    @Column(length = 100)
    private String corp;    // 제작사

    @Column(length = 100)
    private String author;  // 원작

    @Column(length = 100)
    private String director;    // 감독

    @Column(length = 10)
    private String airDate;     // 방영일

    @Column(length = 5)
    private String airTime;     // 방영 시간

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    private DayOfWeekShort dayOfWeek;   // 방영 요일

    @JdbcTypeCode(SqlTypes.JSON)
    @Builder.Default
    private Map<String, String> officalSite = new HashMap<>();     // 관련 사이트

    @JdbcTypeCode(SqlTypes.JSON)
    @Builder.Default
    private Map<String, String> otts = new HashMap<>();  // OTT

    @Column(length = 5)
    private String minAge;    // 시청 등급

    @OneToOne(fetch = FetchType.LAZY)
    private AnimeStar animeStar;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AnimeImg> animeImgs = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AnimeComment> animeComments = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AnimeVoteWeekly> animeVoteWeeklies = new ArrayList<>();
}
