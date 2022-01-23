package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String s);
}
