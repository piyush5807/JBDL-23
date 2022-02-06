package com.example.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) throws JsonProcessingException {
        userService.createUser(userCreateRequest);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("userId") int id){
        return userService.getUser(id);
    }
}
