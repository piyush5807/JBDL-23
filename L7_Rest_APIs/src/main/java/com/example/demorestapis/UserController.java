package com.example.demorestapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static HashMap<Integer, User> userMap = new HashMap<>();

    // POST, GET, DELETE, PUT

    // http://localhost:8080/create_user
    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        userMap.put(user.getId(), user);
    }

    @PostMapping("/user2")
    public void createUser2(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("age") int age){
        User user = User.builder()
                .id(id)
                .name(name)
                .age(age)
                .build();
        userMap.put(id, user);
    }

    @PostMapping("/user3/id/{id}/name/{name}/age/{age}")
    public void createUser3(@PathVariable("id") int id,
                           @PathVariable("name") String name,
                           @PathVariable("age") int age){
        User user = User.builder()
                .id(id)
                .name(name)
                .age(age)
                .build();

        userMap.put(id, user);
    }

    @GetMapping("/users")
    @ResponseBody // responsebody + controller
    public HashMap<Integer, User> getUserMap(){
        return userMap;
    }

    @GetMapping("/user") // controller
    public User getUser(@RequestParam("id") int userId){
        return userMap.get(userId);
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId){
        return userMap.remove(userId);
    }

    @PutMapping("/user")
    public User updateDetailsOfUser(@RequestParam("id") int userId,
                                    @RequestBody User user){

//        userMap.put(userId, user);

        userMap.putIfAbsent(userId, user);
        User retrievedUser = userMap.get(userId);
        if(user.getId() != 0){
            retrievedUser.setId(user.getId());
        }
        if(user.getAge() != 0){
            retrievedUser.setAge(user.getAge());
        }
        if(user.getName() != null){
            retrievedUser.setName(user.getName());
        }

        logger.info("retrieved user from map {}", retrievedUser);
        logger.info("getting user again from map {}", userMap.get(userId));

        return retrievedUser;
    }

    @GetMapping("/test_fun")
    @ResponseBody
    public Map<String, Object> getUsers(@RequestBody User user){

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("age", user.getAge());

        return map;
    }

}
