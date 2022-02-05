package com.example.majorproject2;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCacheRepository {

    private static final String KEY_PREFIX = "user::";

    private final RedisTemplate<String, Object> redisTemplate;

    UserCacheRepository(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void save(User user){
        redisTemplate.opsForValue().set(getKey(user.getId()), user);
    }

    private String getKey(int id){
        return KEY_PREFIX + id;
    }

    public User get(int userId){
        return (User)redisTemplate.opsForValue().get(userId);
    }
}
