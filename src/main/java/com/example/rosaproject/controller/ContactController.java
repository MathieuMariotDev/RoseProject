package com.example.rosaproject.controller;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/add/prospect")
    public String displayFormAddProspect(Model model){
        model.addAttribute("contact",new Contact());
        return "addContactForm";
    }

    @PostMapping("/add/prospect")
    public String prospectSubmit(Contact contact){
        contactService.addProspect(contact);
        return "redirect:/init";
    }

    @GetMapping("/details/{id}")
    public String detailsContact(@PathVariable("id") Long id, Model model){
        model.addAttribute("contact",contactService.findContactById(id));
        return "detailsContact";
    }

    @GetMapping("/delete/{id}")
    public String deleteContactValidation(@PathVariable("id") Long id,Model model){
        model.addAttribute("contact",contactService.findContactById(id));
        return "deleteValidation";
    }

    @PostMapping("/delete/{id}")
    public String submitDeleteContact(@PathVariable("id") Long id){
        contactService.deleteContact(id);
        return "redirect:/contact/home";
    }


}
