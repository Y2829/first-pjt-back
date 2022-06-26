package com.y2829.whai.controller.chat;

import com.y2829.whai.dto.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@Controller
@Slf4j
@CrossOrigin("*")
public class ChatController {

    private final static String NOTICE = "[알림]";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {

        if (ChatMessage.Type.ENTER.equals(message.getType())) {

            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
            message.setSender(NOTICE);

        } else if (ChatMessage.Type.QUIT.equals(message.getType())) {

            message.setMessage(message.getSender() + "님이 방에 나갔습니다.");
            message.setSender(NOTICE);

        }

        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }

}
