package com.duckstar.service;

import com.duckstar.apiPayload.code.status.ErrorStatus;
import com.duckstar.apiPayload.exception.handler.MemberHandler;
import com.duckstar.apiPayload.exception.handler.VoteHandler;
import com.duckstar.apiPayload.exception.handler.WeekHandler;
import com.duckstar.domain.IpVoteRecord;
import com.duckstar.domain.Member;
import com.duckstar.domain.Week;
import com.duckstar.repository.IpVoteRecordRepository;
import com.duckstar.repository.MemberRepository;
import com.duckstar.repository.WeekRepository;
import com.duckstar.web.dto.VoteRequestDto;
import com.duckstar.web.dto.VoteRequestDto.AnimeStarDtoList;
import com.duckstar.web.dto.VoteResponseDto.VoteResultDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final WeekRepository weekRepository;
    private final IpVoteRecordRepository ipVoteRecordRepository;
    private final MemberRepository memberRepository;
    private final AnimeStarService animeStarService;

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    @Transactional
    public VoteResultDto voteAnime(String ipAddress, AnimeStarDtoList request) {
        Week week = weekRepository.findTopByOrderByStartDateTimeDesc().orElseThrow(() ->
                new WeekHandler(ErrorStatus.WEEK_NOT_FOUND));

        // 중복 투표 IP 검사
        if (ipVoteRecordRepository.findByWeekAndIpAddress(week, ipAddress).isPresent()) {
            throw new VoteHandler(ErrorStatus.ALREADY_VOTED_IP);
        }

        // 회원인 경우 마지막 투표 시간 검사
        Long memberId = request.getMemberId();
        Member member = null;
        if (memberId != null) {
            member = memberRepository.findById(memberId).orElseThrow(() ->
                    new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
            LocalDateTime lastAnimeVoteAt = member.getLastAnimeVoteAt();
            if (lastAnimeVoteAt != null &&
                    // 같은 시각도 포함하여 이후인 경우
                      ! lastAnimeVoteAt.isBefore(week.getStartDateTime())) {
                throw new VoteHandler(ErrorStatus.ALREADY_VOTED_MEMBER);
            }
        }

        int votedItemsCount = 0;
        int totalStars = 0;
        for (VoteRequestDto.AnimeStarDto dto : request.getAnimeStarList()) {
            Integer starInt = dto.getStarInt();
            animeStarService.voteAnime(dto.getAnimeId(), starInt);  // 실제 투표 반영
            totalStars += starInt;
            votedItemsCount++;
        }

        // IP 주소 저장
        IpVoteRecord record = IpVoteRecord.builder()
                .week(week)
                .ipAddress(ipAddress)
                .build();

        try {
            ipVoteRecordRepository.save(record);
        } catch (DataIntegrityViolationException e) {
            throw new VoteHandler(ErrorStatus.ALREADY_VOTED_IP);
        }

        // 회원은 투표 시간 업데이트
        if (member != null) member.setLastAnimeVoteAt();

        // 누적 투표 수 Week 테이블에 반영
        weekRepository.incrementAnimeStarsTotal(week.getId(), totalStars);

        return VoteResultDto.builder()
                .ipAddress(ipAddress)
                .totalStars(totalStars)
                .votedItemsCount(votedItemsCount)
                .build();
    }
}
