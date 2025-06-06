package com.freepath.devpath.interview.query.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InterviewRoomDto {
    private Long interviewRoomId;
    private Long userId;
    private String interviewRoomTitle;
    private String interviewCategory;
    private String difficultyLevel;
    private String evaluationStrictness;
    private String interviewRoomStatus;
    private LocalDateTime interviewRoomCreatedAt;
}