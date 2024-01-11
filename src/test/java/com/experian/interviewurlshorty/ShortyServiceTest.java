package com.experian.interviewurlshorty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.NoSuchAlgorithmException;


class ShortyServiceTest {

    @Test
    void hashTest() throws NoSuchAlgorithmException {
        ShortyRedisService shortyService = new ShortyRedisService(Mockito.mock(RedisTemplate.class));
        String hash = shortyService.hash("https://google.com", 6);
        System.out.println(hash);

    }
}