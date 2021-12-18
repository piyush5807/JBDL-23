package com.example.demomysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void createUser(UserCreateRequest userCreateRequest) throws SQLException {

        userDAO.create(userCreateRequest.toUser());
    }

    public User deleteUser(int userId) throws SQLException {
        return userDAO.delete(userId);
    }

    public List<User> getUsers() throws SQLException {
        return userDAO.get();
    }

    public User getUserById(int userId) throws SQLException {
        return userDAO.getById(userId);
    }
}
