package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.CreateContactDto;
import com.example.rosaproject.controller.dto.EchangeDto;
import com.example.rosaproject.controller.dto.EventDto;
import com.example.rosaproject.controller.dto.SearchDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.model.Status;
import com.example.rosaproject.service.ContactService;
import com.example.rosaproject.service.EchangeService;
import com.example.rosaproject.service.EntrepriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    private EntrepriseService entrepriseService;

    private EchangeService echangeService;


    public ContactController(ContactService contactService, EntrepriseService entrepriseService, EchangeService echangeService) {
        this.contactService = contactService;
        this.entrepriseService = entrepriseService;
        this.echangeService = echangeService;
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
        return "redirect:/contact/listProspect";
    }

    @GetMapping("/details/{id}")
    public String detailsContact(@PathVariable("id") Long id, Model model){
        model.addAttribute("contact", contactService.compareDateUpdateContact(contactService.findContactById(id)));
        model.addAttribute("echangeForSubmit",new EchangeDto());
        model.addAttribute("event" , new EventDto());
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
        return "redirect:/contact/details/"+id;
    }

    @GetMapping("/update/client/{id}")
    public String udpdateClientForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("contact",CreateContactDto.fromContact(contactService.findContactById(id)));
        model.addAttribute("entreprises",entrepriseService.getAllEntreprises());
        return "updateClientForm";
    }

    @PostMapping("/update/client/{id}")
    public String updateClientSubmit(@PathVariable("id") Long id,CreateContactDto contact){
        contactService.updateClient(id,contact);
        return "redirect:/contact/details/client/"+id;
    }

    @GetMapping("/details/client/{id}")
    public String detailsClient(@PathVariable("id") Long id, Model model){
        Contact contact = contactService.compareDateUpdateContact(contactService.findContactById(id));
        model.addAttribute("contact",contact);
        String reference = "Client"+contact.getEntreprise().getName();
        List<Echange> listEchangeProspecting = echangeService.findOldEchange(reference);
        List<Echange> listEchangeClient = echangeService.findClientEchange(reference);
        model.addAttribute("echangeForSubmit",new EchangeDto());
        model.addAttribute("clientEchangeList",listEchangeClient);
        model.addAttribute("prospectingEchangeList",listEchangeProspecting);
        model.addAttribute("event" , new EventDto());
        return "detailsClient";
    }



    @GetMapping("/delete/{id}")
    public String deleteContactValidation(@PathVariable("id") Long id,Model model){
        model.addAttribute("contact",contactService.findContactById(id));
        return "deleteValidation";
    }

    @PostMapping("/delete/{id}")
    public String submitDeleteContact(@PathVariable("id") Long id){
        contactService.deleteContact(id);
        return "redirect:/contact/listProspect";
    }

    @GetMapping({"/listProspect","/"})
    public String listViewProspect(Model model,SearchDto search){
        List<Contact> prospectList = new ArrayList<>();
        prospectList = contactService.searchContact(search,false);
        model.addAttribute("searchDto",new SearchDto());
        List<Status> statusList = Arrays.asList(Status.values());
        model.addAttribute("status",statusList);
        model.addAttribute("prospects",prospectList);
        return "prospectListView";
    }

    @GetMapping("/listClient")
    public String listViewClient(Model model,SearchDto search){
        List<Contact> clientList = new ArrayList<>();
        clientList = contactService.searchContact(search,true);
        model.addAttribute("searchDto",new SearchDto());
        model.addAttribute("clients",contactService.getAllClient());

        return "clientListView";
    }

    @PostMapping("/update/toClient/{id}")
    public String toClientSubmit(@PathVariable("id") Long idContact){
        contactService.prospectToClient(idContact);
        return "redirect:/contact/details/client/"+idContact;
    }



}
