package com.duckstar.domain.mapping.character;

import com.duckstar.domain.Character;
import com.duckstar.domain.Member;
import com.duckstar.domain.Week;
import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"week_id", "member_id", "character_id"}),
                @UniqueConstraint(columnNames = {"week_id", "cookie_id", "character_id"})
        }
)
public class CharacterVote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 64)
    private String cookieId;

    /**
     * 당사는 비회원 투표 기능 제공 및 이상 행위 방지를 위해 IP 주소를 수집합니다.
     * 수집된 IP는 투표 중복 방지 및 보안 위협 탐지 외 다른 목적으로 사용되지 않으며,
     * 보관 기간은 최대 3개월 후 자동 파기됩니다.
     */
    @Column(length = 45)
    private String ipAddress;

    private Integer star;

    public void setWeek(Week week) {
        this.week = week;
    }
}
