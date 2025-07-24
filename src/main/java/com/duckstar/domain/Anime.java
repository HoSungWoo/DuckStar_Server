package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.DayOfWeekShort;
import com.duckstar.domain.enums.Medium;
import com.duckstar.domain.mapping.anime.AnimeComment;
import com.duckstar.domain.mapping.anime.AnimeRecord;
import com.duckstar.domain.mapping.anime.AnimeVote;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private LocalDate airDate;     // 방영일

    // 신작이 아니고, 이전 분기에서 넘어와서 방영중인지 여부
    private Boolean isContinuing;

    @Column(length = 5)
    private String airTime;     // 방영 시간

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    private DayOfWeekShort dayOfWeek;   // 방영 요일

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> officialSite = new HashMap<>();     // 관련 사이트

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> otts = new HashMap<>();  // OTT

    @Column(length = 5)
    private String minAge;    // 시청 등급

    @OneToOne(mappedBy = "anime")
    private AnimeImg animeImg;

    @OneToOne(mappedBy = "anime")
    private AnimeStar animeStar;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<Character> characters = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<AnimeComment> animeComments = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<AnimeVote> animeVotes = new ArrayList<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<AnimeRecord> animeRecords = new ArrayList<>();
}
