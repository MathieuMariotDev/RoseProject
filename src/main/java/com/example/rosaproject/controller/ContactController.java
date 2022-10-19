package com.example.rosaproject.controller;

import com.example.rosaproject.controller.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/contact")
public class ContactController {

    @GetMapping("/add/prospect")
    public String displayFormAddProspect(Model model){
        model.addAttribute("contact",new Contact());
        return "addContactForm";
    }

    @PostMapping("/add/prospect")
    public String prospectSubmit(Contact contact){



        return "redirect:/init";
    }

}
