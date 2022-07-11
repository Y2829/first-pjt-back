package com.y2829.whai.api.controller;

import com.y2829.whai.api.dto.ChatMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
@Api(tags = {"메세지 처리에 대한 Controller"})
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
