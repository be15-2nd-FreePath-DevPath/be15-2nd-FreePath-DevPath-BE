package com.freepath.devpath.board.interaction.query.controller;

import com.freepath.devpath.board.interaction.exception.BoardNotFoundException;
import com.freepath.devpath.board.interaction.query.dto.BookmarkedBoardSearchRequest;
import com.freepath.devpath.board.interaction.query.service.BookmarkQueryService;
import com.freepath.devpath.board.post.query.dto.response.PostListResponse;
import com.freepath.devpath.common.dto.ApiResponse;
import com.freepath.devpath.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class BookmarkQueryController {

    private final BookmarkQueryService bookmarkQueryService;

    @GetMapping("/bookmark")
    public ResponseEntity<ApiResponse<PostListResponse>> getBookmarkedPosts(
            @ModelAttribute BookmarkedBoardSearchRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = Integer.parseInt(authentication.getName());

        PostListResponse response = bookmarkQueryService.getBookmarkedPosts(userId, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }



    
    // ===== 컨트롤러 레벨 예외 핸들러들 ===== //

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleBoardNotFoundException(BoardNotFoundException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

}
