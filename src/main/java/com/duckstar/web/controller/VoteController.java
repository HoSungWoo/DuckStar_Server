package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.service.AnimeService;
import com.duckstar.service.VoteService;
import com.duckstar.validation.annotation.CheckAnimeVoteOpen;
import com.duckstar.validation.annotation.CheckCharacterVoteOpen;
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

    private final VoteService voteService;
    private final AnimeService animeService;

    /**
     * 애니메이션 투표
     */
    @Operation(summary = "이번 주차 애니 리스트 전체 조회 API",
            description = "투표를 위해 가나다 순 정렬, 이번 주 투표 기록용 Record 리스트")
    @GetMapping("/anime")
    @CheckAnimeVoteOpen
    public ApiResponse<List<AnimeVotePreviewDto>> getUpcomingAnimeRecords() {
            return ApiResponse.onSuccess(
                    toAnimeVotePreviews(null/*animeService.getUpcomingAnimes()*/));
    }

    @GetMapping("/anime-lameDuck")
    public ApiResponse<List<AnimeVotePreviewDto>> getLameDuckAnimeRecords() {
            return null;
    }

    @Operation(summary = "이번 주차 애니 투표 API",
            description = "이번 주차 애니에 별점을 기록, 투표 시 투표자의 IP 주소를 서버가 DB에 기록, " +
                    "중복 투표 방지 (투표자의 IP 주소 검사, 로그인 회원의 경우 최근 투표 시간 검사)")
    @PostMapping("/anime")
    @CheckAnimeVoteOpen
    public ApiResponse<VoteResultDto> submitAnimeVote(HttpServletRequest httpRequest,
                                                      @RequestBody AnimeStarDtoList request) {
        String clientIp = voteService.getClientIp(httpRequest);
        return ApiResponse.onSuccess(/*voteService.voteAnime(clientIp, request)*/null);
    }

    /**
     * 캐릭터 투표
     */
    @GetMapping("/character")
    @CheckCharacterVoteOpen
    public ApiResponse<Void> getCurrentCharacterRecords() {
        return null;
    }

    @PostMapping("/character")
    @CheckCharacterVoteOpen
    public ApiResponse<VoteResultDto> submitCharacterVote(HttpServletRequest httpRequest,
                                                          @RequestBody CharacterStarDtoList request) {

        return null;
    }
}
