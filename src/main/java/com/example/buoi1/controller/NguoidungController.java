package com.example.buoi1.controller;


import com.example.buoi1.model.User;
import com.example.buoi1.model.UserDemo;
import com.example.buoi1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NguoidungController {

    private final UserService userService;

    public NguoidungController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String trangChiTiet(Model model) {
        model.addAttribute("userName", "Nguyen Van b");
        return "user";
    }
    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }
//    @PostMapping("/addUser")
//    public void saveUser(@ModelAttribute("user") User user) {
//        System.out.println("firstName: "+user.getFirstName());
//        System.out.println("lastName: "+user.getLastName());
//
//    }
    @PostMapping("/addUser")
    public void saveUser(@ModelAttribute("user") UserDemo user) {
        System.out.println("firstName: " + user.getFirstName());
        System.out.println("lastName: " + user.getLastName());

        userService.saveOrUpdate(user);
    }
}
