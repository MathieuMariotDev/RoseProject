package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.CreateUserDto;

import com.example.rosaproject.controller.entity.User;

import com.example.rosaproject.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.thymeleaf.util.StringUtils.substring;


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

    @GetMapping("/showProfile")
    public String displayProfile(Model model){
        User foundUser = userService.findByEmail();
        model.addAttribute("user" , foundUser);
        return "userProfile";
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

    @GetMapping("/allUsers")
    public String displayAllUsers(Model model) {
        List<User> usersList = userService.adminDisplayAllUsers();
        model.addAttribute("users" ,usersList);
        return "adminUsersPage";
    }

    @GetMapping ("/adminDeleteUser/{id}")
    public String deleteUserChoice(@PathVariable("id") Long id,Model model){
        User userFound = userService.findById(id);
        model.addAttribute("user",userFound);
        return "adminDeleteUserValidation";
    }

    @PostMapping("/adminDeleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.AdminDeleteUser(id);
        return "redirect:/user/allUsers";
    }

    @GetMapping ("/deleteUser")
    public String deleteUserChoice(Model model){
        User userFound = userService.findByEmail();
        model.addAttribute("user",userFound);
        return "deleteUserValidation";
    }

    @PostMapping ("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id,Model model){
        userService.deleteUser();
        SecurityContextHolder.clearContext();
        return "redirect:/user/login";
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model) {
        User foundUser = userService.findByEmail();
        CreateUserDto userDto = new CreateUserDto();
      //  String str = userDto.getPicture();
      //  str.substring( 28);
       // userDto.setPictureFile(file);
        CreateUserDto dtoWithInfo =  userDto.toDto(foundUser);
        model.addAttribute("user" , dtoWithInfo);
        return "userEditForm";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, CreateUserDto createUserDto) {
        userService.editUser(createUserDto);
        SecurityContextHolder.clearContext();
        return "redirect:/user/login";
    }

}
