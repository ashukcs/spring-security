package com.techtalk.springwebsecurity.controller;

import com.techtalk.springwebsecurity.model.Role;
import com.techtalk.springwebsecurity.model.User;
import com.techtalk.springwebsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewDashboard(Model model) {
        List<User> userList = userService.findAll();
        userList.stream().forEach(user -> {
            System.out.println(user.toString());
            List<Role> roleList = user.getRoles();
            roleList.stream().forEach(role -> {
                System.out.print(role.toString());
            });
        });
        model.addAttribute("users", userService.findAll());
        return "home";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
