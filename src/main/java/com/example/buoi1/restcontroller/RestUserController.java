package com.example.buoi1.restcontroller;

import com.example.buoi1.model.UserDemo;
import com.example.buoi1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestUserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<UserDemo> users() {
        List<UserDemo> users = userService.getAllUsers();
        return users;
    }

}
