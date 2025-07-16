package com.duckstar.web.controller;

import com.duckstar.apiPayload.ApiResponse;
import com.duckstar.service.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
//@Validated
public class RankController {

    private final AnimeService animeService;

    public ApiResponse<> getAnimeRanksByWeek(/*PathVariable*/) {

    }
}
