package com.freepath.devpath.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorCode {
    // 회원 관련 오류
    USER_NOT_FOUND("10001", "해당 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCHED("10002", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_EMAIL("10003", "이메일 인증이 완료되지 않았습니다.", HttpStatus.UNAUTHORIZED),

    // 게시판 관련 오류
    POST_NOT_FOUND("20001", "해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_CREATION_FAILED("20002", "게시글 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_UPDATE_FAILED("20003", "게시글 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_UPDATE_FORBIDDEN("20004", "게시글을 작성한 사용자의 요청이 아닙니다.", HttpStatus.FORBIDDEN),
    POST_DELETE_FAILED("20005", "게시글 삭제에 실패했습니다.", HttpStatus.NOT_FOUND),
    FILE_DELETE_FAILED("20006", "첨부파일 삭제에 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_DELETE_FORBIDDEN("20007","게시글을 작성한 사용자의 요청이 아닙니다." , HttpStatus.FORBIDDEN),
    POST_ALREADY_DELETED("20008", "이미 삭제된 게시글입니다.", HttpStatus.GONE),

    // ITNews 관련 오류 : 30000번대

    // CSQuiz 관련 오류 : 40000번대

    // 면접 관련 오류 : 50000번대
    INTERVIEW_ROOM_CREATION_FAILED("50001", "면접방을 생성할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_QUESTION_CREATION_FAILED("50002", "면접 질문 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_ROOM_NOT_FOUND("50003", "존재하지 않는 면접방입니다.", HttpStatus.NOT_FOUND),
    INTERVIEW_ROOM_ACCESS_DENIED("50004", "해당 면접방에 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INTERVIEW_INDEX_INVALID("50005", "면접방의 질문 인덱스가 옳지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERVIEW_ANSWER_EMPTY("50006", "면접 답변이 비어있습니다.", HttpStatus.BAD_REQUEST),
    INTERVIEW_EVALUATION_FAILED("50007", "면접 평가 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // Chatting 관련 오류 : 60000번대

    // Report 관련 오류 : 70000번대


    // 공통 오류
    VALIDATION_ERROR("90001", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),
    UNKNOWN_RUNTIME_ERROR("90002", "알 수 없는 런타임 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_ERROR("90003", "알 수 없는 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
