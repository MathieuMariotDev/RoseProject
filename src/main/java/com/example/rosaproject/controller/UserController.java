package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.CreateUserDto;

import com.example.rosaproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/init")
    public String initExample(){
        return "example";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


    @GetMapping("/register")
    public String displayRegisterForm(Model model) {
        model.addAttribute("user" ,new CreateUserDto());
        return "userRegisterForm";
    }

    @PostMapping("/register")
    public String userRegister(CreateUserDto createUserDto) {
       userService.createUser(createUserDto);
        return "redirect:/signin";
    }

    @PostMapping("/delete/{idParam}")
    public String deleteUser(@PathVariable("idParam") Long id){
        userService.deleteUser(id);
        return "redirect:register";
    }




}
