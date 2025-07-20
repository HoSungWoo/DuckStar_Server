package com.duckstar.repository;

import com.duckstar.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findTopByOrderByStartDateTimeDesc();

    @Modifying
    @Query("UPDATE Week w SET w.animeStarsTotal = w.animeStarsTotal + :totalStars WHERE w.id = :id")
    void incrementAnimeStarsTotal(@Param("id") Long id, @Param("totalStars") Integer totalStars);
}