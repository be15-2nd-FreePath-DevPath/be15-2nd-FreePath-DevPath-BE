package com.freepath.devpath.user.command.controller;

import com.freepath.devpath.user.command.dto.UserModifyRequest;

import com.freepath.devpath.user.command.service.UserCommandService;
import com.freepath.devpath.user.command.dto.UserCreateRequest;
import com.freepath.devpath.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserCommandController {
    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> registUser(@RequestBody @Validated UserCreateRequest request) {
        userCommandService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED) // 201 상태코드
                .body(ApiResponse.success(null));
    }

    @PutMapping("/info")
    public ResponseEntity<ApiResponse<Void>> modifyUser(
            @RequestBody @Validated UserModifyRequest request,
            @AuthenticationPrincipal User user) {

        Integer userId = Integer.valueOf(user.getUsername()); // 이제 username 은 userId
        userCommandService.modifyUser(request, userId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
