package com.duckstar.domain.mapping.anime;

import com.duckstar.domain.Anime;
import com.duckstar.domain.AnimeStar;
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
                @UniqueConstraint(columnNames = {"week_start", "member_id", "anime_id"}),
                @UniqueConstraint(columnNames = {"week_start", "ip_address", "anime_id"})
        }
)
public class AnimeVoteWeekly extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 구분
    private Date weekStart;

    // 회원 id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 비회원 투표자 IP 주소
    @Column(length = 45)
    private String ipAddress;   // member나 ipAddress 둘 중 하나만 있어야함 <- 검증 필요

    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    private Float rating;

    //==비즈니스 로직==//

    public void animeStarCount(Float rating) {
        AnimeStar animeStar = anime.getAnimeStar();
        animeStar.starCount(rating);
    }
}