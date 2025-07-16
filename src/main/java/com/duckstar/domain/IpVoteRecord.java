package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class IpVoteRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    // 비회원 투표자 IP 주소
    @Column(length = 45)
    private String ipAddress;   // member나 ipAddress 둘 중 하나만 있어야함 <- 검증 필요
}
