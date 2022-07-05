package com.y2829.whai.chat;

import com.y2829.whai.dto.ChatRoom;
import com.y2829.whai.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisClusterTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChatRoomRepository repo;

    @Test
    public void create_find_ChatRoom_Test() {

        ChatRoom chatRoom = repo.createChatRoom("Test");
        ChatRoom findChatRoom = repo.findRoomById(chatRoom.getRoomId());

        assertThat(chatRoom.getName()).isEqualTo("Test");
        assertThat(findChatRoom.getName()).isEqualTo("Test");

    }
}
