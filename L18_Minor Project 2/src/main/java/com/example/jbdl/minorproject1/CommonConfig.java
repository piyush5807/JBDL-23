package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig extends WebSecurityConfigurerAdapter {

    @Value("${ADMIN_AUTHORITY}")
    private String ADMIN_AUTHORITY;

    @Value("${BOOK_INFO_AUTHORITY}")
    private String BOOK_INFO_AUTHORITY;

    @Value("${STUDENT_INFO_AUTHORITY}")
    private String STUDENT_INFO_AUTHORITY;

    @Value("${STUDENT_ONLY_AUTHORITY}")
    private String STUDENT_ONLY_AUTHORITY;

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/book/**").hasAuthority(BOOK_INFO_AUTHORITY)
                .antMatchers("/book/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers("/studentById/**").hasAuthority(STUDENT_INFO_AUTHORITY) // GET, PUT, DELETE
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers("/student/**").hasAuthority(STUDENT_ONLY_AUTHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

    public LettuceConnectionFactory getRedisFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                "localhost", 6379
        );
//        redisStandaloneConfiguration.setPassword("EDYLWiqGGLSBzi8vHAGANqWhX18QcJp5");
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(getRedisFactory());

        return redisTemplate;
    }

}
