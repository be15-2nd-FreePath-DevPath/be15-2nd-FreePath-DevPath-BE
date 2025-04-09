package com.freepath.devpath.chatting.command.application.service;

import com.freepath.devpath.chatting.command.application.dto.response.ChattingResponse;
import com.freepath.devpath.chatting.command.domain.aggregate.ChattingJoin;
import com.freepath.devpath.chatting.command.domain.aggregate.ChattingJoinId;
import com.freepath.devpath.chatting.command.domain.repository.ChattingJoinRepository;
import com.freepath.devpath.chatting.command.domain.repository.ChattingRepository;
import com.freepath.devpath.chatting.command.domain.aggregate.ChatDTO;
import com.freepath.devpath.chatting.command.domain.aggregate.Chatting;
import com.freepath.devpath.chatting.command.domain.repository.ChattingRoomRepository;
import com.freepath.devpath.chatting.exception.InvalidMessageException;
import com.freepath.devpath.chatting.exception.NoChattingJoinException;
import com.freepath.devpath.chatting.exception.NoSuchChattingRoomException;
import com.freepath.devpath.common.exception.ErrorCode;
import com.freepath.devpath.user.command.entity.User;
import com.freepath.devpath.user.command.repository.UserRepository;
import com.freepath.devpath.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChattingCommandService {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<Integer, Set<Integer>> chatRooms = new HashMap<>();
    private final ChattingRepository chattingRepository;
    private final ChattingJoinRepository chattingJoinRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;

    public void joinRoom(int roomId, int user) {
        chatRooms.computeIfAbsent(roomId, k -> new HashSet<>()).add(user);
        // 입장 시 시스템 메시지 전송
        sendSystemMessage(roomId, user + "님이 입장했습니다.");
        log.info(user+"님이 입장했습니다.");
    }

    public void leaveRoom(int roomId, int user) {
        Set<Integer> users = chatRooms.get(roomId);
        if (users != null && users.remove(user)) {
            sendSystemMessage(roomId, user + "님이 퇴장했습니다.");
            if (users.isEmpty()) {
                chatRooms.remove(roomId);
            }
        }
    }

    @Transactional
    public void sendChatting(ChatDTO chatDTO, Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        //유효성 검사
        //유효한 채팅방인지
        if(chatDTO.getChattingRoomId()!=null && !chattingRoomRepository.existsById(chatDTO.getChattingRoomId())){
            throw new NoSuchChattingRoomException(ErrorCode.NO_SUCH_CHATTING_ROOM);
        }
        //채팅방에 참여중인 유저인지
        Optional<ChattingJoin> join = chattingJoinRepository.findById(new ChattingJoinId(chatDTO.getChattingRoomId(),userId));
        if(join.isEmpty() || join.get().getChattingJoinStatus()=='N'){
            throw new NoChattingJoinException(ErrorCode.NO_CHATTING_JOIN);
        }
        //유효한 메세지인지
        if(chatDTO.getChattingMessage()==null){
            throw new InvalidMessageException(ErrorCode.INVALID_MESSAGE);
        }
        Chatting chatting = Chatting.builder()
                .chattingRoomId(chatDTO.getChattingRoomId())
                .userId(userId)
                .chattingMessage(chatDTO.getChattingMessage())
                .chattingCreatedAt(LocalDateTime.now())
                .build();

        Chatting savedChatting = chattingRepository.save(chatting);
        User user = userRepository.findById((long)userId).orElseThrow(
                () -> new UserException(ErrorCode.USER_NOT_FOUND)
        );

        ChattingResponse chattingResponse = ChattingResponse.builder()
                .message(savedChatting.getChattingMessage())
                .timestamp(savedChatting.getChattingCreatedAt().toString())
                .nickname(user.getNickname())
                        .build();
        messagingTemplate.convertAndSend("/topic/room/" + chatting.getChattingRoomId(), chattingResponse);
    }

    @Transactional
    public void sendSystemMessage(int roomId, String content) {
        Chatting chatting = Chatting.builder()
                .chattingRoomId(roomId)
                .userId(1)
                .chattingMessage(content)
                .chattingCreatedAt(LocalDateTime.now())
                .build();

        chattingRepository.save(chatting);
        messagingTemplate.convertAndSend("/topic/room/" + roomId, chatting);
    }



}
