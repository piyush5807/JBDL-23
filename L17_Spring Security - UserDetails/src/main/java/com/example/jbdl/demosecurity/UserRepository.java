package com.example.jbdl.demosecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

    @Query("select u from MyUser u where u.user_name = :s")
    UserDetails find(String s);
}
