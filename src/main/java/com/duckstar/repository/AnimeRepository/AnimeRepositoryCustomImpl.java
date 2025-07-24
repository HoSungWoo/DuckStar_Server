package com.duckstar.repository.AnimeRepository;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.*;
import com.duckstar.globalUtil.QuarterUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.duckstar.web.dto.AnimeResponseDto.*;
import static com.duckstar.web.dto.CharacterResponseDto.*;
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
                        anime.officialSite,
                        anime.otts,
                        anime.minAge,
                        animeImg.imageUrl,
                        animeStar.star_1_0,
                        animeStar.star_2_0,
                        animeStar.star_3_0,
                        animeStar.star_4_0,
                        animeStar.star_5_0)
                .from(anime)
                .leftJoin(animeStar).on(animeStar.anime.id.eq(anime.id))
                .leftJoin(animeImg).on(animeImg.anime.id.eq(anime.id))
                .where(anime.id.eq(animeId))
                .fetchOne();

        if (animeData == null) {
            throw new AnimeHandler(ErrorStatus.ANIME_NOT_FOUND);
        }

        // DTO 생성자 기반 Projection
        List<CharacterPreviewDto> characters = queryFactory
                .select(Projections.constructor(CharacterPreviewDto.class,
                        character.id,
                        character.nameKor,
                        character.nameEng,
                        character.nameKanji,
                        character.cv,
                        characterImg.imageUrl))  //TODO 썸네일로 변경필요
                .from(character)
                .leftJoin(characterImg).on(characterImg.character.id.eq(character.id))
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
                .officalSite(animeData.get(anime.officialSite))
                .otts(animeData.get(anime.otts))
                .minAge(animeData.get(anime.minAge))
                .imageUrl(animeData.get(animeImg.imageUrl))

                .starDistributeDto(StarDistributeDto.builder()
                        .star_1_0(animeData.get(animeStar.star_1_0))
                        .star_2_0(animeData.get(animeStar.star_2_0))
                        .star_3_0(animeData.get(animeStar.star_3_0))
                        .star_4_0(animeData.get(animeStar.star_4_0))
                        .star_5_0(animeData.get(animeStar.star_5_0))
                        .build())

                .characterPreviewDtos(characters)

                .aniCommentPreviewDtos(Collections.emptyList()) // TODO
                .build();
    }

    public List<Anime> getCurrentQuarterAnimes() {
        LocalDate today = LocalDate.now();
        LocalDate quarterStart = QuarterUtil.getStartDateOfQuarter(today);
        LocalDate quarterEnd = QuarterUtil.getEndDateOfQuarter(today);

        BooleanBuilder builder = new BooleanBuilder();
        // 이번 분기 신작 애니
        builder.or(anime.airDate.between(quarterStart, quarterEnd));
        // 이전 분기에서 넘어온 애니
        builder.or(anime.isContinuing.isTrue());

        // 데이터 조회
        return queryFactory.selectFrom(anime)
                .where(builder)
                .leftJoin(animeImg).on(animeImg.anime.id.eq(anime.id)).fetchJoin()
                .orderBy(anime.nameKor.asc()) //TODO 가나다순? 인기순? 랜덤?
                .fetch();
    }
}
