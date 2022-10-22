package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.CreateContactDto;
import com.example.rosaproject.controller.dto.SearchDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.service.ContactService;
import com.example.rosaproject.service.EntrepriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    private EntrepriseService entrepriseService;






    public ContactController(ContactService contactService, EntrepriseService entrepriseService) {
        this.contactService = contactService;
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/add/prospect")
    public String displayFormAddProspect(Model model){
        model.addAttribute("contact",new CreateContactDto());
        model.addAttribute("entreprises",entrepriseService.getAllEntreprises());
        return "addContactForm";
    }

    @PostMapping("/add/prospect")
    public String prospectSubmit(CreateContactDto createContactDto){
        contactService.addProspect(createContactDto);
        return "redirect:/init";
    }

    @GetMapping("/details/{id}")
    public String detailsContact(@PathVariable("id") Long id, Model model){
        model.addAttribute("contact",contactService.findContactById(id));
        model.addAttribute("echangeForSubmit",new Echange());
        return "detailsContact";
    }

    @GetMapping("/update/{id}")
    public String udpdateContactForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("contact",CreateContactDto.fromContact(contactService.findContactById(id)));
        model.addAttribute("entreprises",entrepriseService.getAllEntreprises());
        return "updateContactForm";
    }

    @PostMapping("/update/{id}")
    public String updateContactSubmit(@PathVariable("id") Long id,CreateContactDto contact){
        contactService.updateContact(id,contact);
        return "redirect:/home";
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

    @GetMapping({"/listProspect","/"})
    public String listViewProspect(Model model,SearchDto search){
        List<Contact> prospectList = new ArrayList<>();
        if(search !=null){
            prospectList = contactService.searchProspect(search);
        }else{
            prospectList = contactService.getAllProspect();
        }
        model.addAttribute("searchDto",new SearchDto());
        model.addAttribute("prospects",prospectList);
        return "prospectListView";
    }

    @GetMapping("/listClient")
    public String listViewClient(Model model){
        model.addAttribute("clients",contactService.getAllClient());
        return "clientListView";
    }
}
