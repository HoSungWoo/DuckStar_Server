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

    public List<Anime> getAnimes() {
        return animeRepository.findAll();
    }
}
