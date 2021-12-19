package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() throws SQLException {
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws SQLException {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        try{
            userService.createUser(userCreateRequest);
            return new ResponseEntity<String>("User is created", HttpStatus.CREATED);
        }catch (SQLException e){
            return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId) throws SQLException {
        return userService.deleteUser(userId);
    }
}
