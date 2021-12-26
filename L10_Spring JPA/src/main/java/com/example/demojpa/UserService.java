package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void createUser(UserCreateRequest userCreateRequest) throws SQLException {

        userDAO.save(userCreateRequest.toUser());
    } // 10 times

    public List<User> createBulkUsers(List<UserCreateRequest> userCreateRequests){ // 1 time with 10 records

        List<User> users = userCreateRequests.stream()
                .map(userCreateRequest -> userCreateRequest.toUser())
                .collect(Collectors.toList());

        return userDAO.saveAll(users);
    }

    public User deleteUser(int userId) throws SQLException {
        User user = userDAO.getById(userId); // User$HibernateProxy
        userDAO.deleteById(userId);
        return user;
    }

    public List<User> getUsers() throws SQLException {
        return userDAO.findAll();
    }

    public User getUserById(int userId) throws SQLException {
        return userDAO.findById(userId).orElse(null);
    }
}
