package com.example.buoi1.controller;

import com.example.buoi1.model.Role;
import com.example.buoi1.model.UserDemo;
import com.example.buoi1.repository.RoleRepository;
import com.example.buoi1.repository.UserRepository;
import com.example.buoi1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/register")
    public String register(Model model) {
//        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("user", new UserDemo());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "register";
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestBody UserDemo userDemo) {
//        if (userRepository.findByEmail(userDemo.getEmail()) != null) {
//            return "User already exists!";
//        }
//
//        // Mã hóa mật khẩu
//        userDemo.setPassword(passwordEncoder.encode(userDemo.getPassword()));
//
//        // Gán role USER
//        Role userRole = roleRepository.findByName("USER");
//        if (userRole == null) {
//            userRole = new Role();
//            userRole.setName("USER");
//            roleRepository.save(userRole);
//        }
//        userDemo.setRoles(Collections.singleton(userRole));
//
//        userRepository.save(userDemo);
//        return "User registered successfully!";
//    }
@PostMapping("/register")
public String registerUser(@ModelAttribute UserDemo userDemo) {
    if (userRepository.findByEmail(userDemo.getEmail()) != null) {
        return "redirect:/auth/register?error=UserExists";
    }

    // Mã hóa mật khẩu
    userDemo.setPassword(passwordEncoder.encode(userDemo.getPassword()));

    // Gán role USER
    Role userRole = roleRepository.findByName("USER");
    if (userRole == null) {
        userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);
    }
    userDemo.setRoles(Collections.singleton(userRole));

    userRepository.save(userDemo);
    return "redirect:/login?success";
}

}
