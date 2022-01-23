package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.User;
import com.example.jbdl.minorproject1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

        // select * from user where username = :s
        // select * from student where user_id = :s
    }

    public User saveUser(User user){
       return userRepository.save(user);
    }
}
