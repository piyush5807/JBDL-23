package com.example.demomysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
        //TODO: Return all the users in the system
        return userService.getUsers();
    }

    /*
         1. Inserting in user table
         2. Selecting that record which was inserted
         3. Updated any col of that record
     */

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws SQLException {
        //TODO: Return user with given id
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public void createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) throws SQLException {
        //TODO: Creating a new user with given details
        userService.createUser(userCreateRequest);
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId) throws SQLException {
        // TODO: Deleting a user
        return userService.deleteUser(userId);
    }

    // 1 csv file
    /*
        Name,Age,Email
        Lin,12,lin@google.com
     */

}
