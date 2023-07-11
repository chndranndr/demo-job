package com.example.job.controller;

import com.example.job.entities.User;
import com.example.job.entities.UserCredentials;
import com.example.job.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "Invalid credentials";
        }

        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, "jfJxzlzLVLkkgZggJx8zw7kSsxI6aG83Q2LgmGZJvnc=") // TODO secure key
                .compact();

        return token;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        if (userRepository.findByUsername(username) != null) {
            return "Username already exists";
        }

        User user = new User(username, password);
        userRepository.save(user);

        return "User registered successfully";
    }

}

