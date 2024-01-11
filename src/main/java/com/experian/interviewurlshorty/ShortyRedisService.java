package com.experian.interviewurlshorty;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service("redis")
public class ShortyRedisService implements ShortyService {
    private RedisTemplate<String, String> redis;

    private MessageDigest digest;

    public ShortyRedisService(RedisTemplate<String, String> redis) throws NoSuchAlgorithmException {
        this.redis = redis;
        digest = MessageDigest.getInstance("SHA-256");
    }

    @Override
    public String shorten(String url) {
        String hash = hash(url);
        redis.opsForValue().set(hash, url);

        return hash;
    }

    public String hash(String url) {
        return hash(url, 6);
    }

    public String hash(String url, Integer length) {
        byte[] bytes = digest.digest(url.getBytes());
        String hash = String.format("%32x", new BigInteger(1, bytes));

        return hash.substring(0, Math.min(length, hash.length()));
    }

    @Override
    public String resolve(String hash) {
        String url = redis.opsForValue().get(hash);
        if (StringUtils.isBlank(url)) {
            throw new HashNotFoundException(String.format("%s not found", hash));
        }
        return url;
    }
}
