package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.service.AnimeService;
import com.duckstar.web.dto.AnimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
//@Validated
public class RankController {

    private final AnimeService animeService;

//    @GetMapping("/anime/{year}/{quarter}/{week}")
//    public ApiResponse<> getAnimeRanksByWeek(@PathVariable Integer year,
//                                             @PathVariable Integer quarter,
//                                             @PathVariable Integer week) {
//
//    }
}
