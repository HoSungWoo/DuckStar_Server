package com.duckstar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StarInfoDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StarDistributeDto {
        Float starAverage;

        Integer star_0_5;
        Integer star_1_0;
        Integer star_1_5;
        Integer star_2_0;
        Integer star_2_5;
        Integer star_3_0;
        Integer star_3_5;
        Integer star_4_0;
        Integer star_4_5;
        Integer star_5_0;
//
//        public static StarDistributeDto from(AnimeStar animeStar) {
//            return StarDistributeDto.builder()
//                    .star_0_5(animeStar.getStar_0_5())
//                    .star_1_0(animeStar.getStar_1_0())
//                    .star_1_5(animeStar.getStar_1_5())
//                    .star_2_0(animeStar.getStar_2_0())
//                    .star_2_5(animeStar.getStar_2_5())
//                    .star_3_0(animeStar.getStar_3_0())
//                    .star_3_5(animeStar.getStar_3_5())
//                    .star_4_0(animeStar.getStar_4_0())
//                    .star_4_5(animeStar.getStar_4_5())
//                    .star_5_0(animeStar.getStar_5_0())
//                    .build();
//        }
    }
}
