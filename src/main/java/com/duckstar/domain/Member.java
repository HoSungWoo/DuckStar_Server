package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.mapping.anime.AnimeComment;
import com.duckstar.domain.mapping.anime.AnimeCommentLike;
import com.duckstar.domain.mapping.character.CharacterComment;
import com.duckstar.domain.mapping.character.CharacterCommentLike;
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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 500)
    private String profileImgUrl;

    private LocalDateTime lastAnimeVoteAt;

    private LocalDateTime lastCharacterVoteAt;

    /**
     * 소셜 로그인
     */
    @Column(length = 20)
    private String provider;    // 소셜 로그인 제공자 (KAKAO, NAVER, APPLE)

    @Column(length = 100, unique = true)
    private String providerId;  // 소셜 로그인 제공자로부터 받은 사용자 ID

    @Column(length = 1000)  // 소셜 액세스 토큰 저장
    private String socialAccessToken;

    @Column(length = 1000)
    private String refreshToken;    // 서버 자체 발급 리프레시 토큰

    /**
     * 애니메이션
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AnimeComment> animeComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AnimeCommentLike> animeCommentLikes = new ArrayList<>();

    /**
     * 캐릭터
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CharacterComment> characterComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CharacterCommentLike> characterCommentLikes = new ArrayList<>();
}
