package com.duckstar.repository;

import com.duckstar.domain.mapping.anime.AnimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRecordRepository extends JpaRepository<AnimeRecord, Long> {

    List<AnimeRecord> findAllByWeek_IdOrderByAnime_NameKorAsc(Long weekId);
}
