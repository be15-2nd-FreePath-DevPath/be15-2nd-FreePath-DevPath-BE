package com.freepath.devpath.user.query.controller;

import com.freepath.devpath.common.auth.service.AuthService;
import com.freepath.devpath.common.response.ApiResponse;
import com.freepath.devpath.email.service.EmailService;
import com.freepath.devpath.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserQueryController {
    private final UserQueryService userQueryService;
    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<Void>> findLoginIdTemp(@RequestParam String email) {
        authService.validateUserStatusByEmail(email); // 제재 여부, 탈퇴 확인
        emailService.sendCheckEmail(email);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(null));
    }


    @PostMapping("/find-id")
    public ResponseEntity<ApiResponse<String>> findLoginId(@RequestBody String email){
        String loginId = userQueryService.findLoginIdByEmail(email);

        return ResponseEntity.ok(ApiResponse.success(loginId));
    }
}
