package com.duckstar.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class VoteStatusManager {
    private final AtomicBoolean isAnimeOpen = new AtomicBoolean(true);
    private final AtomicBoolean isCharacterOpen = new AtomicBoolean(true);

    public boolean isAnimeVoteOpen() {
        return isAnimeOpen.get();
    }

    public boolean isCharacterVoteOpen() {
        return isCharacterOpen.get();
    }

    public void openAnimeVote() {
        isAnimeOpen.set(true);
    }

    public void closeAnimeVote() {
        isAnimeOpen.set(false);
    }

    public void openCharacterVote() {
        isCharacterOpen.set(true);
    }

    public void closeCharacterVote() {
        isCharacterOpen.set(false);
    }
}