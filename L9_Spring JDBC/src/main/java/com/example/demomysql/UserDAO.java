package com.example.demomysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

  // DDL - Data definition language (index, alter, create a new table)
  // DML - Data manipulation language (insert into, update, delete)

  // CRUD - Create, Read, Update and Delete

  private final Connection connection;

  public UserDAO() throws SQLException {
    this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
    createTable();
  }

  public void createTable() throws SQLException {
      Statement statement = this.connection.createStatement();
      statement.execute("create table if not exists my_user (id int primary key auto_increment, firstName varchar(20), lastName varchar(10), age int , isSeniorCitizen bool, email varchar(30) unique)");
  }

  public void create(User user) throws SQLException {
      PreparedStatement statement = this.connection.prepareStatement("insert into my_user(firstName, lastName, age, email, isSeniorCitizen) VALUES(?, ?, ?, ?, ?)");

      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());
      statement.setInt(3, user.getAge());
      statement.setString(4, user.getEmail());
      statement.setBoolean(5, user.isSeniorCitizen());

      int result = statement.executeUpdate();

      logger.debug("Number of records inserted: {}", result);

  }

  public List<User> get() throws SQLException {

      List<User> userList = new ArrayList<>();

      Statement statement = this.connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from my_user");

      while(resultSet.next()){
//          int id = resultSet.getInt(1);

          int id = resultSet.getInt("id");
          String firstName = resultSet.getString("firstName");
          String lastName = resultSet.getString("lastName");
          String email = resultSet.getString("email");
          int age = resultSet.getInt("age");
          boolean isSeniorCitizen = resultSet.getBoolean("isSeniorCitizen");

          User user = User.builder()
                  .id(id)
                  .firstName(firstName)
                  .lastName(lastName)
                  .email(email)
                  .age(age)
                  .isSeniorCitizen(isSeniorCitizen)
                  .build();
          userList.add(user);
      }

      return userList;
  }

  public User getById(int id) throws SQLException {
      Statement statement = this.connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from my_user where id = " + id);

      while(resultSet.next()){
//          int id = resultSet.getInt(1);

          int userId = resultSet.getInt("id");
          String firstName = resultSet.getString("firstName");
          String lastName = resultSet.getString("lastName");
          String email = resultSet.getString("email");
          int age = resultSet.getInt("age");
          boolean isSeniorCitizen = resultSet.getBoolean("isSeniorCitizen");

          return User.builder()
                  .id(userId)
                  .firstName(firstName)
                  .lastName(lastName)
                  .email(email)
                  .age(age)
                  .isSeniorCitizen(isSeniorCitizen)
                  .build();
      }

      return null;

  }

  public User delete(int id) throws SQLException {
      User user = getById(id);
      Statement statement = this.connection.createStatement();
      int rowsUpdated = statement.executeUpdate("delete from my_user where id = " + id);

      logger.debug("Number of records deleted {}", rowsUpdated);

      return user;
  }

  public void update(int id, User user){
      String sql = "UPDATE my_user SET ";
      if(user.getAge() != 0){
          sql += "id = " + user.getAge();
      }

      sql += " where id = " + id;

  }

  // UPDATE my_user set where id =

}
