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
    private final QAnimeImg animeImg = QAnimeImg.animeImg;
    private final QCharacter character = QCharacter.character;
    private final QCharacterImg characterImg = QCharacterImg.characterImg;

    // 1. animeData → Tuple이 길어질 경우, AnimeInfoDto 같은 DTO로 projection해도 됨
    // 2. characterImgExpr → isMain = true 조건 컬럼으로도 대체 가능 (쿼리 최적화시)
    // 3. 향후 댓글 쿼리도 CommentPreviewDto로 projection하면 같은 방식으로 확장 가능
    public AnimeHomeDto getAnimeHomeDtoById(Long animeId) {

        // Anime + 이미지 리스트
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
                        anime.minAge)
                .from(anime)
                .where(anime.id.eq(animeId))
                .fetchOne();

        // 커스텀 애노테이션 고려 가능
        if (animeData == null) {
            throw new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND);
        }

        AnimeStar animeStar = queryFactory
                .selectFrom(QAnimeStar.animeStar)
                .where(QAnimeStar.animeStar.anime.id.eq(animeId))
                .fetchOne();

        List<String> imageUrls = queryFactory
                .select(animeImg.imageUrl)
                .from(animeImg)
                .where(animeImg.anime.id.eq(animeId))
                .orderBy(animeImg.id.asc()) // or isMain, priority 등
                .fetch();

        // Character + 대표 이미지 1장
        JPQLQuery<String> characterImgExpr = JPAExpressions
                .select(characterImg.imageUrl)
                .from(characterImg)
                .where(characterImg.character.eq(character))
                .orderBy(characterImg.id.asc())
                .limit(1);

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
                .starDistributeDto(StarDistributeDto.from(animeStar))
                .characterPreviewDtos(characters)
                .aniCommentPreviewDtos(Collections.emptyList()) // TODO
                .build();
    }
}
