package com.y2829.whai.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void connectionTest() {

        final String topic = "Coding";
        final Object message = "Hello!";

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(topic, message);

        String result = (String) valueOperations.get(topic);

        assertThat(message).isEqualTo(result);
    }

}
