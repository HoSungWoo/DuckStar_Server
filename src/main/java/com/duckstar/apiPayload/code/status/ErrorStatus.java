package com.duckstar.apiPayload.code.status;


import com.duckstar.apiPayload.code.BaseErrorCode;
import com.duckstar.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 주차
    WEEK_NOT_FOUND(HttpStatus.BAD_REQUEST, "WEEK4001", "존재하지 않는 주차입니다."),

    // 애니메이션
    ANIME_STAR_NOT_VALID(HttpStatus.BAD_REQUEST, "ANIME4001", "잘못된 별점 형식입니다."),
    ANIME_NOT_FOUND(HttpStatus.BAD_REQUEST, "ANIME4002", "존재하지 않는 애니메이션입니다."),

    // 캐릭터
    CHARACTER_STAR_NOT_VALID(HttpStatus.BAD_REQUEST, "CHARACTER4001", "잘못된 별점 형식입니다."),

    // 투표
    ALREADY_VOTED_IP(HttpStatus.BAD_REQUEST, "VOTE4001", "이미 투표한 IP입니다."),
    ALREADY_VOTED_MEMBER(HttpStatus.BAD_REQUEST, "VOTE4002", "이미 투표한 회원입니다."),
    VOTE_CLOSED(HttpStatus.BAD_REQUEST, "VOTE4003", "투표가 진행 중이 아닙니다."),

    // 멤버
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "존재하지 않는 회원입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
