package com.freepath.devpath.chatting.command.application.controller;

import com.freepath.devpath.chatting.command.application.service.ChattingCommandService;
import com.freepath.devpath.chatting.command.application.dto.request.ChattingRequest;
import com.freepath.devpath.chatting.command.application.dto.response.ChattingListResponse;
import com.freepath.devpath.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "채팅 관리", description = "채팅 전송/조회 기능 API")
public class ChattingCommandController {
    //@MessageMapping()의 경로가 "/chat/message"이지만 ChatConfig의 setApplicationDestinationPrefixes()를 통해
    // prefix를 "/app"으로 해줬기 때문에 실질 경로는 "/app/chat/message"가 됨
    //클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면,
    //메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 됨.

    private final ChattingCommandService chattingService;
    //실제 요청은 app이 선행되어야 한다.
    @MessageMapping("/chatting/send")
    public void send(ChattingRequest chattingRequest, Principal principal) {
        log.info(principal.getName()+" : "+ chattingRequest.toString());
        int userId = Integer.parseInt(principal.getName());
        chattingService.sendChatting(chattingRequest,userId);
    }

    @Operation(summary = "채팅 내역 조회", description = "채팅방 ID를 이용하여 채팅 내역을 조회한다.")
    @GetMapping("/chatting/list/{chattingRoomId}")
    public ResponseEntity<ApiResponse<ChattingListResponse>> loadChattingList(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable int chattingRoomId
    ){
        int userId = Integer.parseInt(userDetails.getUsername());
        ChattingListResponse chattingListResponse = chattingService.getChattingList(userId, chattingRoomId);

        return ResponseEntity.ok(ApiResponse.success(chattingListResponse));
    }
}
