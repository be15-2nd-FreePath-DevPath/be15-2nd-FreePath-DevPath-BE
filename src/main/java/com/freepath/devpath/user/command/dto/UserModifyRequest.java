package com.freepath.devpath.user.command.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserModifyRequest {
    private final String currentPassword;
    private final String newPassword;
    private final String nickname;
    private final String itNewsSubscription;
}
