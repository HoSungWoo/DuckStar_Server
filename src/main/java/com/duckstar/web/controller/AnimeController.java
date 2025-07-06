package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.service.AnimeService;
import com.duckstar.web.dto.AnimeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animes")
@RequiredArgsConstructor
//@Validated
public class AnimeController {

    private final AnimeService animeService;

    @Operation(summary = "애니 단건 조회 API",
            description = "애니메이션 단건 정보를 조회합니다.")
    @GetMapping("/{animeId}")
    public ApiResponse<AnimeResponseDto.AnimeHomeDto> getAnimeDetails(
            @PathVariable Long animeId) {

        return ApiResponse.onSuccess(animeService.getAnimeHomeDto(animeId));
    }
}
