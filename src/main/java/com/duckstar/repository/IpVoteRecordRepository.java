package com.duckstar.repository;

import com.duckstar.domain.IpVoteRecord;
import com.duckstar.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IpVoteRecordRepository extends JpaRepository<IpVoteRecord, Long> {
    Optional<IpVoteRecord> findByWeekAndIpAddress(Week week, String ipAddress);
}
