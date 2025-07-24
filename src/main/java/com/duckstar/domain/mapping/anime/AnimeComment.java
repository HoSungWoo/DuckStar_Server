package com.duckstar.domain.mapping.anime;

import com.duckstar.domain.Anime;
import com.duckstar.domain.Member;
import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.UserCommentType;
import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.comments.CommentType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimeComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Boolean isSpoiler;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private UserCommentType commentType;

    @Column(length = 300, nullable = false)
    private String body;

    @OneToMany(mappedBy = "animeComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimeCommentLike> animeCommentLikes = new ArrayList<>();
}
