package com.duckstar.domain.mapping.character;

import com.duckstar.domain.Character;
import com.duckstar.domain.CharacterStar;
import com.duckstar.domain.Member;
import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"week_start", "member_id", "character_id"}),
                @UniqueConstraint(columnNames = {"week_start", "ip_address", "character_id"})
        }
)
public class CharacterVoteWeekly extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 구분
    private Date weekStart;

    // 회원 id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 비회원 투표자 IP 주소
    @Column(length = 45)
    private String ipAddress;   // member나 ipAddress 둘 중 하나만 있어야함 <- 검증 필요

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    private Float rating;

    public void characterStarCount(Float rating) {
        CharacterStar characterStar = character.getCharacterStar();
        characterStar.starCount(rating);
    }
}
