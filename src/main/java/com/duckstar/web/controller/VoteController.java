package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.repository.AnimeRepository.AnimeRepository;
import com.duckstar.service.AnimeService;
import com.duckstar.service.VoteService;
import com.duckstar.web.dto.AnimeResponseDto;
import com.duckstar.web.dto.AnimeResponseDto.AnimeVotePreviewDto;
import com.duckstar.web.dto.VoteRequestDto.AnimeStarDtoList;
import com.duckstar.web.dto.VoteRequestDto.CharacterStarDtoList;
import com.duckstar.web.dto.VoteResponseDto.VoteResultDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.duckstar.converter.AnimeConverter.toAnimeVotePreviews;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final AnimeRepository animeRepository;
    private final VoteService voteService;
    private final AnimeService animeService;

    /**
     * 애니메이션 투표
     */
    @Operation(summary = "현재 분기 애니메이션 전체 조회 API",
            description = "투표를 위해 가나다 순 정렬된 현재 분기 애니메이션 리스트")
    @GetMapping("/anime")
    public ApiResponse<List<AnimeVotePreviewDto>> getCurrentQuarterAnimes() {
        return ApiResponse.onSuccess(
                toAnimeVotePreviews(animeRepository.getCurrentQuarterAnimes()));
    }

    @Operation(summary = "현재 분기 애니메이션 투표 API",
            description = "애니메이션들에 별점을 반영, 투표 시 투표자의 IP 주소를 서버가 DB에 기록, " +
                    "중복 투표 방지 (투표자의 IP 주소 검사, 로그인 회원의 경우 최근 투표 시간 검사)")
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
