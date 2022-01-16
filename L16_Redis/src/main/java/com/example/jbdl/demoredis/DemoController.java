package com.example.jbdl.demoredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String KEY_PREFIX = "person::";
    private static final String PERSON_LIST_KEY = "person_list";
    private static final String HASH_KEY_PREFIX = "person_hk::";

    // ------------------------- Value operations ----------------------------

    @PostMapping("/setValue")
    public void setValue(@Valid @RequestBody Person person){
        String key = KEY_PREFIX + person.getId();
        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/getValue")
    public Person getValue(@RequestParam("id") int personId){
        String key = KEY_PREFIX + personId;
        return (Person) redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/getAllValues") // Getting all the person
    public List<Person> getAllPerson(){
        String pattern = KEY_PREFIX + "*";
        return redisTemplate.keys(pattern).stream()
                .sorted()
                .map(key -> redisTemplate.opsForValue().get(key))
                .map(obj -> (Person) obj)
                .collect(Collectors.toList());
    }


    // ------------------------ List operations -------------------------

    @PostMapping("/lpush")
    public void lpush(@Valid @RequestBody Person person){
        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY, person);
    }

    @PostMapping("/rpush")
    public void rPush(@Valid @RequestBody Person person){
        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY, person);
    }

    @DeleteMapping("/lpop")
    public void lpop(@RequestParam(value = "count", required = false,
            defaultValue = "1") Integer count){
        redisTemplate.opsForList().leftPop(PERSON_LIST_KEY, count);
    }

    @PostMapping("/rpop")
    public void rpop(@RequestParam(value = "count", required = false,
            defaultValue = "1") int count){
        redisTemplate.opsForList().rightPop(PERSON_LIST_KEY, count);
    }

    @GetMapping("/lrange")
    public List<Person> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                               @RequestParam(value = "end", required = false, defaultValue = "-1") int end){

        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end).stream()
                .map(x -> (Person)x)
                .collect(Collectors.toList());

    }

    // ------------------ Hash Operations ------------------------------

    @PostMapping("/hmset")
    public void hmset(@Valid @RequestBody Person person){

        String key = HASH_KEY_PREFIX + person.getId();

        // Person --> Map<String, Object> {"name": "ABC", "id": 1, ...}
        Map map = objectMapper.convertValue(person, Map.class);
        redisTemplate.opsForHash().putAll(key, map);
    }

    @GetMapping("/hgetall")
    public Person hgetall(@RequestParam("id") int id){
        String key = HASH_KEY_PREFIX + id;
        Map map = redisTemplate.opsForHash().entries(key);

        return objectMapper.convertValue(map, Person.class);
    }

    // TODO: hdel, hset, expire, setex, ttl, expireat



}
