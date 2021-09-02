package com.techtalk.springwebsecurity.controller;

import com.techtalk.springwebsecurity.mapper.UserDto;
import com.techtalk.springwebsecurity.model.User;
import com.techtalk.springwebsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result) {

        User existing = userService.findByUserName(userDto.getUserName());
        if (existing != null) {
            result.rejectValue("userName", null, "There is already an account registered with that userName");
        }

        if (result.hasErrors()) {
            return "signup";
        }

        userService.save(userDto);
        return "redirect:/signup?success";
    }
}
