package com.duckstar.web.dto;

import com.duckstar.domain.enums.LikeState;
import com.duckstar.domain.enums.UserCommentType;
import com.duckstar.web.dto.MemberResponseDto.MemberProfileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AniCommentResponseDto {

    @Builder
    @Getter
    public static class AniCommentPreviewDto {
        // 글쓴이
        MemberProfileDto memberProfileDto;

        // 로그인 유저
        @Schema(description = "좋아요 ENUM (LIKE, DISLIKE, NONE)")
        LikeState loginUserLikeState;

        // 애니 댓글
        Long animeCommentId;

        @Schema(description = "베스트 여부 ENUM (NORMAL, BEST)")
        UserCommentType commentType;

        Boolean isSpoiler;
        String body;
        Integer likeCount;
        Integer dislikeCount;
        LocalDateTime createdAt;
    }
}
