package com.duckstar.repository.AnimeRepository;

import com.duckstar.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long>, AnimeRepositoryCustom {
}