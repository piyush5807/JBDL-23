package com.example.jbdl.demosecurity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser implements UserDetails {

    private static final String DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String user_name;

    private String password;

    private String authorities;  // deploy:monitor:develop:


//    private boolean accountLocked;
//    private boolean credentialsExpired;
//    private boolean accountExpired;
//
//    private boolean accountEnabled;


    // authority
    /*
        1. A single user can have multiple authorities (CEO /Developer / Manager)  -> monitor

                        Developer    Manager     CEO      Director  VP
         Deploy             Y            Y         N        N        N
         Monitoring         Y            Y         y        y        Y


         Designation
         .antMatchers("/monitor/**").hasAnyAuthority("Developer", "Manager", "CEO", "Director")
         .antMatchers("/deploy/**").hasAnyAuthority("Developer", "Manager")


         Roles that a user performs
         .antMatchers("/monitor/**").hasAuthority("monitor")
         .antMatchers("/deploy/**").hasAuthority("deploy")
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authority_list = this.authorities.split(DELIMITER);

        return Arrays.stream(authority_list)
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.user_name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
