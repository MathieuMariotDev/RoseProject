package com.example.rosaproject.controller;

import com.example.rosaproject.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @GetMapping("/init")
    public String initExample(){
        return "example";
    }







}
