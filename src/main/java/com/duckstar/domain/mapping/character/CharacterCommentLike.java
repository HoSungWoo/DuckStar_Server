package com.duckstar.domain.mapping.character;

import com.duckstar.domain.Member;
import com.duckstar.domain.common.BaseEntity;
import com.duckstar.domain.enums.LikeState;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CharacterCommentLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_comment_id", nullable = false)
    private CharacterComment characterComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private LikeState likeState;    // LIKE, DISLIKE, NONE
}
