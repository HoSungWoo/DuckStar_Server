package com.duckstar.repository;

import com.duckstar.domain.mapping.anime.AnimeRecordWeekly;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimeRecordWeeklyRepository extends JpaRepository<AnimeRecordWeekly, Long> {

    @EntityGraph(attributePaths = {"anime", "anime.animeImg"})
    List<AnimeRecordWeekly> findAllByWeekIdOrderByAnime_NameKorAsc(Long weekId);

    //TODO 사용자 많아지면 -> 캐싱 필요
    // ''' Redis 등 캐시 서버에 INCR로 투표 먼저 적재
    // 이후 비동기 혹은 배치 처리로 DB에 반영 '''

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_0_5 = a.star_0_5 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_0_5(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_1_0 = a.star_1_0 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_1_0(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_1_5 = a.star_1_5 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_1_5(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_2_0 = a.star_2_0 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_2_0(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_2_5 = a.star_2_5 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_2_5(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_3_0 = a.star_3_0 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_3_0(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_3_5 = a.star_3_5 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_3_5(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_4_0 = a.star_4_0 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_4_0(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_4_5 = a.star_4_5 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_4_5(@Param("animeRecordId") Long animeRecordId);

    @Modifying
    @Query("UPDATE AnimeRecordWeekly a" +
            " SET a.star_5_0 = a.star_5_0 + 1 WHERE a.id =:animeRecordId")
    void incrementStar_5_0(@Param("animeRecordId") Long animeRecordId);
}
