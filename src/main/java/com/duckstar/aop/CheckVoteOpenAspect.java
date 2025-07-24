package com.duckstar.aop;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.VoteHandler;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckVoteOpenAspect {

    private final VoteStatusManager voteStatusManager;

    @Before("@annotation(com.duckstar.validation.annotation.CheckAnimeVoteOpen)")
    public void validateAnimeVoteOpen() {
        if (!voteStatusManager.isAnimeVoteOpen()) {
            throw new VoteHandler(ErrorStatus.VOTE_CLOSED);
        }
    }

    @Before("@annotation(com.duckstar.validation.annotation.CheckCharacterVoteOpen)")
    public void validateCharacterVoteOpen() {
        if (!voteStatusManager.isCharacterVoteOpen()) {
            throw new VoteHandler(ErrorStatus.VOTE_CLOSED);
        }
    }
}
