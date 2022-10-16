package com.example.rosaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/init")
    public String initExample(){
        return "example";
    }





}
