package com.freepath.devpath.user.command.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeEmailRequest {
    @Email
    private final String currentEmail;
    private final String newEmail;
}
