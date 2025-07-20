package com.duckstar.repository.AnimeRepository;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.*;
import com.duckstar.web.dto.CharacterPreviewDto;
import com.duckstar.web.dto.QCharacterPreviewDto;
import com.duckstar.web.dto.StarInfoDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.duckstar.web.dto.AnimeResponseDto.*;
import static com.duckstar.web.dto.StarInfoDto.*;

@Repository
@RequiredArgsConstructor
public class AnimeRepositoryCustomImpl implements AnimeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QAnime anime = QAnime.anime;
    private final QAnimeStar animeStar = QAnimeStar.animeStar;
    private final QAnimeImg animeImg = QAnimeImg.animeImg;
    private final QCharacter character = QCharacter.character;
    private final QCharacterImg characterImg = QCharacterImg.characterImg;

    // 1. 제공하는 별 데이터는 실제 데이터이면 절대 ❌ (비밀 투표. 실시간성 띠면 안됨)
    //     -- 직전, 이전 ... 주차 등등 데이터? 기획 논의 필요
    // 2. 향후 댓글 쿼리도 CommentPreviewDto로 projection할지 여부 선택
    public AnimeHomeDto getAnimeHomeDtoById(Long animeId) {

        // Anime, AnimeStar join
        Tuple animeData = queryFactory
                .select(anime.id,
                        anime.medium,
                        anime.nameKor,
                        anime.nameOrigin,
                        anime.genre,
                        anime.corp,
                        anime.author,
                        anime.director,
                        anime.airDate,
                        anime.airTime,
                        anime.dayOfWeek,
                        anime.officalSite,
                        anime.otts,
                        anime.minAge,
                        animeImg.imageUrl,
                        animeStar.star_0_5,
                        animeStar.star_1_0,
                        animeStar.star_1_5,
                        animeStar.star_2_0,
                        animeStar.star_2_5,
                        animeStar.star_3_0,
                        animeStar.star_3_5,
                        animeStar.star_4_0,
                        animeStar.star_4_5,
                        animeStar.star_5_0)
                .from(anime)
                .leftJoin(animeStar)
                .on(animeStar.anime.id.eq(anime.id))
                .leftJoin(animeImg)
                .on(animeImg.anime.id.eq(anime.id))
                .where(anime.id.eq(animeId))
                .fetchOne();

        if (animeData == null) {
            throw new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND);
        }

        // Character 리스트 QueryProjection
        List<CharacterPreviewDto> characters = queryFactory
                .select(new QCharacterPreviewDto(
                        character.id,
                        character.nameKor,
                        character.nameEng,
                        character.nameKanji,
                        character.cv,
                        characterImg.thumbnailUrl
                ))
                .from(character)
                .leftJoin(characterImg)
                .on(characterImg.character.id.eq(character.id))
                .where(character.anime.id.eq(animeId))
                .orderBy(character.id.asc())  // id 정렬
                .fetch();

        return AnimeHomeDto.builder()
                .medium(animeData.get(anime.medium))
                .nameKor(animeData.get(anime.nameKor))
                .nameOrigin(animeData.get(anime.nameOrigin))
                .genre(animeData.get(anime.genre))
                .corp(animeData.get(anime.corp))
                .author(animeData.get(anime.author))
                .director(animeData.get(anime.director))
                .airDate(animeData.get(anime.airDate))
                .airTime(animeData.get(anime.airTime))
                .dayOfWeek(animeData.get(anime.dayOfWeek))
                .officalSite(animeData.get(anime.officalSite))
                .otts(animeData.get(anime.otts))
                .minAge(animeData.get(anime.minAge))
                .imageUrl(animeData.get(animeImg.imageUrl))

                .starDistributeDto(StarDistributeDto.builder()
                        .star_0_5(animeData.get(animeStar.star_0_5))
                        .star_1_0(animeData.get(animeStar.star_1_0))
                        .star_1_5(animeData.get(animeStar.star_1_5))
                        .star_2_0(animeData.get(animeStar.star_2_0))
                        .star_2_5(animeData.get(animeStar.star_2_5))
                        .star_3_0(animeData.get(animeStar.star_3_0))
                        .star_3_5(animeData.get(animeStar.star_3_5))
                        .star_4_0(animeData.get(animeStar.star_4_0))
                        .star_4_5(animeData.get(animeStar.star_4_5))
                        .star_5_0(animeData.get(animeStar.star_5_0))
                        .build())
                .characterPreviewDtos(characters)
                .aniCommentPreviewDtos(Collections.emptyList()) // TODO
                .build();
    }
}
