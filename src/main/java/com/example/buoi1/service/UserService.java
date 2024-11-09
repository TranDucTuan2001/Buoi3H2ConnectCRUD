package com.example.buoi1.service;

import com.example.buoi1.model.UserDemo;

import com.example.buoi1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveOrUpdate(UserDemo user) {
        userRepository.save(user);
    }
    // Thêm phương thức getAllUsers vào UserService
    public List<UserDemo> getAllUsers() {
        return userRepository.findAll();
    }

}