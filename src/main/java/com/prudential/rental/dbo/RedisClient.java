package com.prudential.rental.dbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RedisClient {
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        RedisClient.stringRedisTemplate = redisTemplate;
    }

    public static List<String> Range(String key){
        return stringRedisTemplate.opsForList().range(key,0,-1L);
    }

    public static long Decr(String key){
        return stringRedisTemplate.opsForValue().increment(key, -1L);
    }

    public static long Get(String key){
        return Long.valueOf(stringRedisTemplate.opsForValue().get(key));
    }

    public static boolean Exist(String key){
        return stringRedisTemplate.hasKey(key);
    }


}
