package com.duckstar.domain;

import com.duckstar.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(name = "idx_week_ip", columnList = "week_id, ip_address")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"week_id", "ip_address"}),
        }
)
public class IpVoteRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    // 비회원 투표자 IP 주소
    @Column(length = 45, nullable = false)
    private String ipAddress;
}