package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.service.AnimeService;
import com.duckstar.service.VoteService;
import com.duckstar.web.dto.AnimeResponseDto;
import com.duckstar.web.dto.AnimeResponseDto.AnimeVotePreviewsPage;
import com.duckstar.web.dto.VoteRequestDto.AnimeStarDtoList;
import com.duckstar.web.dto.VoteRequestDto.CharacterStarDtoList;
import com.duckstar.web.dto.VoteResponseDto.VoteResultDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final AnimeService animeService;

    /**
     * 애니메이션 투표
     */
    @GetMapping("/anime")
    public ApiResponse<AnimeVotePreviewsPage> getAnimeList() {
        return null;
    }

    @PostMapping("/anime")
    public ApiResponse<VoteResultDto> submitAnimeVote(HttpServletRequest httpRequest,
                                                      @RequestBody AnimeStarDtoList request) {
        String clientIp = voteService.getClientIp(httpRequest);
        return ApiResponse.onSuccess(voteService.voteAnime(clientIp, request));
    }

    /**
     * 캐릭터 투표
     */
    @GetMapping("/character")
    public ApiResponse<Void> getCharacterList() {
        return null;
    }

    @PostMapping("/character")
    public ApiResponse<VoteResultDto> submitCharacterVote(HttpServletRequest httpRequest,
                                                          @RequestBody CharacterStarDtoList request) {

        return null;
    }
}
