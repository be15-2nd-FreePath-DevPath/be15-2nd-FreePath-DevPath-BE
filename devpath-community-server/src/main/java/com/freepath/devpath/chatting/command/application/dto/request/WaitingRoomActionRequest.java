package com.freepath.devpath.chatting.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WaitingRoomActionRequest {
    private int chattingRoomId;
    private int inviteeId;
    private WaitingRoomAction action;
}
