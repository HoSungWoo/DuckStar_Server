package com.duckstar.service;

import com.duckstar.domain.Anime;
import com.duckstar.repository.CharacterRepository;
import com.duckstar.web.dto.CharacterPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<CharacterPreviewDto> getCharacterPreviewDtos(Anime anime) {

//        List<Character> characters = characterRepository.getCharactersByAnimeId(anime.getId());
        return null;
    }
}
