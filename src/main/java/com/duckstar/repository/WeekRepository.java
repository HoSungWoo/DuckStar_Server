package com.duckstar.repository;

import com.duckstar.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week, Long> {

    @Modifying
    @Query("UPDATE Week w SET w.animeStarTotal = w.animeStarTotal + :starTotal WHERE w.id = :id")
    void incrementAnimeStarTotal(@Param("id") Long id, @Param("totalStars") Integer starTotal);
}