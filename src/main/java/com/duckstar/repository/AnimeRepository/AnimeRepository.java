package com.duckstar.repository.AnimeRepository;

import com.duckstar.domain.Anime;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long>, AnimeRepositoryCustom {
}