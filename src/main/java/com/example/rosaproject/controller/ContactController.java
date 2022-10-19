package com.example.rosaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/contact")
public class ContactController {

    @GetMapping("/add/prospect")
    public String displayFormAddProspect(){
        return "addContactForm";
    }

}
