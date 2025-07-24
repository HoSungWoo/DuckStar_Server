package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.character.CharacterComment;
import com.duckstar.domain.mapping.character.CharacterRecord;
import com.duckstar.domain.mapping.character.CharacterVote;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "characters")  // MySQL 예약어 문제로 s 붙임
public class Character extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")  // id 이름 명시
    private Long id;

    private String nameKor;
    private String nameEng;
    private String nameKanji;

    private String cv;  // 성우

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    @OneToOne(mappedBy = "character")
    private CharacterImg characterImg;

    @OneToOne(mappedBy = "character")
    private CharacterStar characterStar;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterComment> characterComments = new ArrayList<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterVote> characterVotes = new ArrayList<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterRecord> characterRecords = new ArrayList<>();
}
