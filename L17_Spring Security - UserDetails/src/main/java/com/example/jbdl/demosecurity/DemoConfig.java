package com.example.jbdl.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoConfig extends WebSecurityConfigurerAdapter {

    @Value("${ADMIN_AUTHORITY}")
    private String ADMIN_AUTHORITY;

    @Value("${STUDENT_ATTENDANCE_AUTHORITY}")
    private String STUDENT_ATTENDANCE_AUTHORITY;

    @Value("${STUDENT_ONLY_AUTHORITY}")
    private String STUDENT_ONLY_AUTHORITY;


    @Autowired
    MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // we can define how we want to authenticate
        auth.userDetailsService(myUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we can define our authorization rules

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/student/attendance/**").hasAuthority(STUDENT_ATTENDANCE_AUTHORITY)
                .antMatchers("/student/**").hasAuthority(STUDENT_ONLY_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
