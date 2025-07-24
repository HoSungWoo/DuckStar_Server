package com.duckstar.domain.mapping.character;

import com.duckstar.domain.Character;
import com.duckstar.domain.Member;
import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.UserCommentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Boolean isSpoiler;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private UserCommentType commentType;

    @Column(length = 300, nullable = false)
    private String body;

    @OneToMany(mappedBy = "characterComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterCommentLike> characterCommentLikes = new ArrayList<>();
}