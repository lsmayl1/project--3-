package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        List<User> accounts = userRepository.findAll();
        for (User user : accounts) {
            if (user.getLogin().equals(username)) return user;
        }
        return null;
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
