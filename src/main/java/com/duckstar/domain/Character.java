package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.character.CharacterComment;
import com.duckstar.domain.mapping.character.CharacterRecordWeekly;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "ani_character")
public class Character extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Builder.Default
    private List<CharacterRecordWeekly> characterRecordWeeklies = new ArrayList<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CharacterComment> characterComments = new ArrayList<>();
}
