package com.duckstar.repository;

import com.duckstar.domain.AnimeStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimeStarRepository extends JpaRepository<AnimeStar, Long> {

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_0_5 = a.star_0_5 + 1 WHERE a.id = :id")
    void incrementStar_0_5(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_1_0 = a.star_1_0 + 1 WHERE a.id = :id")
    void incrementStar_1_0(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_1_5 = a.star_1_5 + 1 WHERE a.id = :id")
    void incrementStar_1_5(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_2_0 = a.star_2_0 + 1 WHERE a.id = :id")
    void incrementStar_2_0(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_2_5 = a.star_2_5 + 1 WHERE a.id = :id")
    void incrementStar_2_5(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_3_0 = a.star_3_0 + 1 WHERE a.id = :id")
    void incrementStar_3_0(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_3_5 = a.star_3_5 + 1 WHERE a.id = :id")
    void incrementStar_3_5(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_4_0 = a.star_4_0 + 1 WHERE a.id = :id")
    void incrementStar_4_0(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_4_5 = a.star_4_5 + 1 WHERE a.id = :id")
    void incrementStar_4_5(@Param("id") Long id);

    @Modifying
    @Query("UPDATE AnimeStar a SET a.star_5_0 = a.star_5_0 + 1 WHERE a.id = :id")
    void incrementStar_5_0(@Param("id") Long id);
}