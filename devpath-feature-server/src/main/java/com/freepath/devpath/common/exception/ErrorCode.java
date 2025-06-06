package com.freepath.devpath.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorCode {
    // 회원 관련 오류
    USER_NOT_FOUND("10001", "해당 회원은 찾을 수 없거나 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCHED("10002", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_EMAIL("10003", "이메일 인증이 완료되지 않았습니다.", HttpStatus.UNAUTHORIZED),
    EMAIL_ALREADY_EXISTS("10004", "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
    SAME_AS_OLD_PASSWORD("10005", "이전 비밀번호와 같은 비밀번호는 사용할 수 없습니다.", HttpStatus.CONFLICT),
    LOGIN_ID_ALREADY_EXISTS("10006", "이미 사용중인 ID 입니다.", HttpStatus.CONFLICT),
    SOCIAL_LOGIN_USER("10007", "소셜 로그인 사용자는 해당 기능을 사용할 수 없습니다.", HttpStatus.UNAUTHORIZED),
    SOCIAL_SIGNUP_EXPIRED("10008", "소셜 회원가입 유효 시간이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    NICKNAME_ALREADY_USED("10009", "이미 사용중인 닉네임입니다.", HttpStatus.CONFLICT),
    INVALID_NICKNAME("10010", "닉네임 공백일 수 없습니다.", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS("10011", "올바르지 않은 아이디 혹은 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    RESTRICTED_USER("10012", "제재된 사용자입니다.", HttpStatus.FORBIDDEN),

    // 게시판 관련 오류
    POST_NOT_FOUND("20001", "해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_CREATION_FAILED("20002", "게시글 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_UPDATE_FAILED("20003", "게시글 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_UPDATE_FORBIDDEN("20004", "게시글을 작성한 사용자의 요청이 아닙니다.", HttpStatus.FORBIDDEN),
    POST_DELETE_FAILED("20005", "게시글 삭제에 실패했습니다.", HttpStatus.NOT_FOUND),
    FILE_DELETE_FAILED("20006", "첨부파일 삭제에 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_DELETE_FORBIDDEN("20007","게시글을 작성한 사용자의 요청이 아닙니다." , HttpStatus.FORBIDDEN),
    POST_ALREADY_DELETED("20008", "이미 삭제된 게시글입니다.", HttpStatus.GONE),
    POST_ALREADY_REPORTED("20009", "이미 삭제된 게시글입니다.", HttpStatus.BAD_REQUEST),

    // 댓글 관련 오류
    COMMENT_NOT_FOUND("25001", "댓글 또는 대댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    COMMENT_ACCESS_DENIED("25002", "수정 권한이 없습니다.", HttpStatus.FORBIDDEN),
    COMMENT_DELETE_DENIED("25003", "본인이 작성한 댓글만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN),
    COMMENT_INVALID_ARGUMENT("25004", "댓글 또는 대댓글 중 하나만 지정해야 합니다.", HttpStatus.BAD_REQUEST),
    POST_SEARCH_FAILED("20009", "검색할 수 있는 기간은 최대 4주입니다.", HttpStatus.BAD_REQUEST),


    // 투표 관련 오류
    VOTE_ALREADY_VOTED("22001", "이미 해당 투표에 참여하셨습니다.", HttpStatus.BAD_REQUEST),
    VOTE_END_FAILED("22002", "해당 투표를 생성한 회원이 아닙니다.", HttpStatus.BAD_REQUEST),
    VOTE_ALREADY_ENDED("22003", "이미 종료된 투표입니다.", HttpStatus.BAD_REQUEST),

    // interaction 관련 오류 : 23000번대
    ALREADY_LIKED("23001","이미 좋아요를 했습니다.",HttpStatus.CONFLICT),
    BOARD_NOT_FOUND("23002","해당 게시글을 찾을 수 없습니다.",HttpStatus.NOT_FOUND),
    LIKE_NOT_FOUND("23004", "좋아요 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CANNOT_LIKE_BOTH("23005", "게시글과 댓글에 동시에 좋아요를 할 수 없습니다.", HttpStatus.BAD_REQUEST),
    INVALID_LIKE_TARGET("23006", "게시글 ID나 댓글 ID 중 하나는 필수입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_BOOKMARKED("23007", "이미 북마크를 했습니다.", HttpStatus.CONFLICT),
    BOOKMARK_NOT_FOUND("23008","북마크 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // ITNews 관련 오류 : 30000번대

    // 이메일 인증 실패 추가
    INVALID_EMAIL_AUTH_CODE("30001", "이메일 인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_REGISTERED_TEMP("30002", "인증은 성공했지만, 회원 정보가 없습니다.", HttpStatus.BAD_REQUEST),
    NEWS_NOT_FOUND("30003", "해당 뉴스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_AUTH_PURPOSE("30004","유효하지 않은 인증 목적입니다", HttpStatus.BAD_REQUEST),

    // CSQuiz 관련 오류 : 40000번대
    CS_QUIZ_NOT_FOUND("40001","해당 CS 퀴즈를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 면접 관련 오류 : 50000번대
    INTERVIEW_ROOM_CREATION_FAILED("50001", "면접방을 생성할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_QUESTION_CREATION_FAILED("50002", "면접 질문 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_ROOM_NOT_FOUND("50003", "존재하지 않는 면접방입니다.", HttpStatus.NOT_FOUND),
    INTERVIEW_ROOM_ACCESS_DENIED("50004", "해당 면접방에 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INTERVIEW_INDEX_INVALID("50005", "면접방의 질문 인덱스가 옳지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERVIEW_ANSWER_EMPTY("50006", "면접 답변이 비어있습니다.", HttpStatus.BAD_REQUEST),
    INTERVIEW_EVALUATION_FAILED("50007", "면접 평가 생성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_SUMMARY_NOT_FOUND("50008","면접 총평이 생성되지 않았습니다.", HttpStatus.NOT_FOUND),
    INTERVIEW_ROOM_DELETE_FAILED("50009", "면접방 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_ROOM_TITLE_INVALID("50010", "면접방 제목은 빈 값이 될 수 없습니다.", HttpStatus.BAD_REQUEST),


    INTERVIEW_QUERY_CREATION_FAILED("51001", "면접 조회 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_ROOM_QUERY_NOT_FOUND("51002", "존재하지 않는 면접방을 조회할 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERVIEW_ROOM_QUERY_NO_OWNER("51003", "소유자가 없는 면접방입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERVIEW_QUERY_ACCESS_DENIED("51004", "조회할 수 없는 면접방입니다.", HttpStatus.FORBIDDEN),
    INTERVIEW_HISTORY_NOT_FOUND("51005", "면접방에 대한 면접 이력이 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    // Chatting 관련 오류 : 60000번대
    USER_ALREADY_BLOCKED("60001","이미 차단한 사용자입니다.",HttpStatus.CONFLICT),
    USER_NOT_BLOCKED("60002","차단하지 않은 사용자입니다.",HttpStatus.NOT_FOUND),
    NO_CHATTING_JOIN("60003","참여중인 채팅방이 아닙니다.",HttpStatus.NOT_FOUND),
    NO_SUCH_CHATTING_ROOM("60004","유효한 채팅방이 아닙니다.", HttpStatus.NOT_FOUND),
    CHATTING_ROOM_ALREADY_EXISTS("60005","이미 생성된 채팅방입니다.",HttpStatus.BAD_REQUEST),
    INVALID_MESSAGE("60006","유효한 메세지가 아닙니다.",HttpStatus.BAD_REQUEST),
    ALREADY_CHATTING_JOIN("60007","이미 참여중인 채팅방입니다.",HttpStatus.BAD_REQUEST),
    NO_CHATTING_ROOM_AUTH("60008", "채팅방 권한이 없습니다.", HttpStatus.FORBIDDEN),
    USER_NOT_WAITING("60009","참여 요청중이 아닙니다." ,HttpStatus.FORBIDDEN ),
    INVALID_TITLE("60010","올바르지 않은 제목입니다." ,HttpStatus.BAD_REQUEST ),

    // Report 관련 오류 : 70000번대
    REPORT_NOT_FOUND("70001", "해당 신고 내역이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    REPORT_ALREADY_CHECKED("70002", "이미 처리된 신고 검토 내역입니다", HttpStatus.BAD_REQUEST),

    // 공통 오류
    VALIDATION_ERROR("90001", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),
    UNKNOWN_RUNTIME_ERROR("90002", "알 수 없는 런타임 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_ERROR("90003", "알 수 없는 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
