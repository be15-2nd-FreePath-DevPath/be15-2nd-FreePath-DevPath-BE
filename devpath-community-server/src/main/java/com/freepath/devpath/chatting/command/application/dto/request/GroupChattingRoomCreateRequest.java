package com.freepath.devpath.chatting.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupChattingRoomCreateRequest {
    int boardId;
    String chattingRoomTitle;
}
