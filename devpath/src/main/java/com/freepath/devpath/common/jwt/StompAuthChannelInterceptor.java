package com.freepath.devpath.common.jwt;

import com.freepath.devpath.chatting.command.application.service.ChattingJoinCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Slf4j
@RequiredArgsConstructor
public class StompAuthChannelInterceptor implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChattingJoinCommandService chattingJoinCommandService;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor
                .getAccessor(message, StompHeaderAccessor.class);
        String token = accessor.getFirstNativeHeader("Authorization");
        if (StompCommand.CONNECT.equals(accessor.getCommand()) ||
                StompCommand.SEND.equals(accessor.getCommand())
        ) {
            //유효하지 않은 토큰인 경우
            if (token == null || !jwtTokenProvider.validateToken(token)) {
                throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            }
            int userId = Integer.parseInt(jwtTokenProvider.getUsernameFromJWT(token));
            Principal principal = () -> Integer.toString(userId);
            accessor.setUser(principal);
        }
        else if(StompCommand.SUBSCRIBE.equals(accessor.getCommand())){
            //유효하지 않은 토큰인 경우
            if (token == null || !jwtTokenProvider.validateToken(token)) {
                throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            }
            int userId = Integer.parseInt(jwtTokenProvider.getUsernameFromJWT(token));
            String destination = accessor.getDestination();
            chattingJoinCommandService.isUserAllowedToSubscribe(userId, destination);
        }

        return message;
    }
}

