package com.duckstar.repository;

import com.duckstar.domain.AnimeStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimeStarRepository extends JpaRepository<AnimeStar, Long> {
}