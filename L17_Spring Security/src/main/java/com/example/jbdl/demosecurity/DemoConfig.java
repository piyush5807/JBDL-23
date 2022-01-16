package com.example.jbdl.demosecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
public class DemoConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // we can define how we want to authenticate
        auth.inMemoryAuthentication()
                .withUser("sagar")
                .password("$2a$10$3WXW6xCW2./cKRDCBSClcuMkHvc.mAYNgFDXfP/jZVidCyGD6ECnO")
                .authorities("admin")
                .and()
                .withUser("priyank")
                .password("$2a$10$3TTW.jvQKY0WaOG5iaPy6.eOFQRWmeZjKVXkULHsuIKC7JJZXkZQi")
                .authorities("student");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we can define our authorization rules

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/student/attendance/**").hasAnyAuthority("student", "admin")
                .antMatchers("/student/**").hasAuthority("student")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
