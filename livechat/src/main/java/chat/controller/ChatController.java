package chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import chat.dto.ChatMessageDTO;
import chat.model.ChatMessage;


@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessageDTO sendMessage(ChatMessage chatMessage) {
        // 메시지 처리 로직
        // ChatMessage를 ChatMessageDTO로 변환하여 반환
        return new ChatMessageDTO(chatMessage.getSender(), chatMessage.getContent());
    }

}
