package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.AnimeHandler;
import com.duckstar.domain.Anime;
import com.duckstar.domain.AnimeImg;
import com.duckstar.domain.AnimeStar;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnimeService {

    private final AnimeRepository animeRepository;

//    private StarDistributeDto getStarDistribute(Anime anime) {
//        AnimeStar animeStar = anime.getAnimeStar();
//        return StarDistributeDto.builder()
//                .star_0_5(animeStar.getStar_0_5())
//                .star_1_0(animeStar.getStar_1_0())
//                .star_1_5(animeStar.getStar_1_5())
//                .star_2_0(animeStar.getStar_2_0())
//                .star_2_5(animeStar.getStar_2_5())
//                .star_3_0(animeStar.getStar_3_0())
//                .star_3_5(animeStar.getStar_3_5())
//                .star_4_0(animeStar.getStar_4_0())
//                .star_4_5(animeStar.getStar_4_5())
//                .star_5_0(animeStar.getStar_5_0())
//                .build();
//    }
}
