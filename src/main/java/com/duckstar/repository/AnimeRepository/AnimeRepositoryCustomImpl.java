package com.duckstar.repository.AnimeRepository;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.*;
import com.duckstar.web.dto.CharacterPreviewDto;
import com.duckstar.web.dto.QCharacterPreviewDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.duckstar.web.dto.AnimeResponseDto.*;

@Repository
@RequiredArgsConstructor
public class AnimeRepositoryCustomImpl implements AnimeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QAnime anime = QAnime.anime;
    private final QAnimeStar animeStar = QAnimeStar.animeStar;
    private final QAnimeImg animeImg = QAnimeImg.animeImg;
    private final QCharacter character = QCharacter.character;
    private final QCharacterImg characterImg = QCharacterImg.characterImg;

    // 1. characterImgExpr → isMain = true 조건 컬럼으로도 대체 가능 (쿼리 최적화 가능) - 즉 이미지에 isMain 필드 두자는 뜻
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
                .leftJoin(anime.animeStar, animeStar)
                .on(animeStar.anime.id.eq(anime.id))
                .where(anime.id.eq(animeId))
                .fetchOne();

        // 커스텀 애노테이션 고려 가능
        if (animeData == null) {
            throw new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND);
        }

        // Anime 이미지 리스트
        List<String> imageUrls = queryFactory
                .select(animeImg.imageUrl)
                .from(animeImg)
                .where(animeImg.anime.id.eq(animeId))
                .orderBy(animeImg.id.asc()) // or isMain, priority 등
                .fetch();

        // Character 리스트 QueryProjection
        // Character 대표 이미지 1장
        JPQLQuery<String> characterImgExpr = JPAExpressions
                .select(characterImg.imageUrl)
                .from(characterImg)
                .where(characterImg.character.eq(character))
                .orderBy(characterImg.id.asc())
                .limit(1);

        // Characters
        List<CharacterPreviewDto> characters = queryFactory
                .select(new QCharacterPreviewDto(
                        character.id,
                        character.nameKor,
                        character.nameEng,
                        character.nameKanji,
                        character.cv,
                        characterImgExpr
                ))
                .from(character)
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

                .imageUrls(imageUrls)
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
