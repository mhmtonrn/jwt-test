package com.softengine.jwttest;

import com.softengine.jwttest.entity.User;
import com.softengine.jwttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtTestApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUser(){
        List<User> users = Stream.of(
                new User(1,"Mehmet","Onar","mmoanr2@m.com"),
                new User(2,"dudu","Onar","mmoanr2@m.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtTestApplication.class, args);
    }

}
