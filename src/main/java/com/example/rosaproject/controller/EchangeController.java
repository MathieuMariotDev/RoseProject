package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.EchangeDto;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.service.ContactService;
import com.example.rosaproject.service.EchangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/echange")
public class EchangeController {

    ContactService contactService;

    EchangeService echangeService;

    public EchangeController(ContactService contactService, EchangeService echangeService) {
        this.contactService = contactService;
        this.echangeService = echangeService;
    }

    @GetMapping("/delete/{id}/{idEchange}")
    public String deleteEchangeValidation(@PathVariable("id") Long idContact,@PathVariable("idEchange") Long idEchange,Model model){
        model.addAttribute("contact",contactService.findContactById(idContact));
        model.addAttribute("echange",echangeService.getEchangeById(idEchange));
        return "deleteEchangeValidation";
    }

    @PostMapping("/delete/{id}/{idEchange}")
    public String validateDeleteEchange(@PathVariable("id") Long idContact,@PathVariable("idEchange") Long idEchange){
        echangeService.deleteEchange(idEchange);
        return "redirect:/contact/details/"+idContact;
    }

    @PostMapping("/delete/client/{id}/{idEchange}")
    public String validateDeleteEchangeClient(@PathVariable("id") Long idContact,@PathVariable("idEchange") Long idEchange){
        echangeService.deleteEchange(idEchange);
        return "redirect:/contact/details/client/"+idContact;
    }




    @GetMapping("/update/{id}/{idEchange}")
    public String updateEchangeForm(@PathVariable("id") Long idContact,@PathVariable("idEchange") Long idEchange,Model model){
        model.addAttribute("contact",contactService.findContactById(idContact));
        model.addAttribute("echange",echangeService.getEchangeById(idEchange));
        return "updateEchangeForm";
    }

    @PostMapping("/update/{id}/{idEchange}")
    public String submitUpdateEchange(@PathVariable("id") Long idContact, @PathVariable("idEchange") Long idEchange, Echange echangeFromView){
        echangeService.updateEchange(idEchange,echangeFromView);
        return "redirect:/contact/details/"+idContact;
    }

    @PostMapping("/add/{id}")
    public String submitAddEchange(@PathVariable("id") Long idContact,Echange echange){
        echangeService.addEchange(idContact,echange);
        return "redirect:/contact/details/"+idContact;
    }

    @PostMapping("/add/client/{id}")
    public String submitAddEchangeToClient(@PathVariable("id") Long idContact,Echange echange){
        echangeService.addEchangeForClient(echange,idContact);
        return "redirect:/contact/details/client/"+idContact;
    }


    @PostMapping("/add/resume/note/{id}")
    public String submitAddRelanceNote(@PathVariable("id") Long idContact,Echange echange){
        echangeService.addResumeNote(idContact,echange);
        return "redirect:/contact/details/"+idContact;
    }

    @PostMapping("/add/resume/timer/{id}")
    public String submitAddResumeTimer(@PathVariable("id") Long idContact, EchangeDto echange){
        echangeService.addResumeTimer(idContact,echange);
        return "redirect:/contact/details/"+idContact;
    }

    @PostMapping("/add/client/resume/timer/{id}")
    public String submitAddContactTimer(@PathVariable("id") Long idContact,EchangeDto echange){
        echangeService.addContactTimerForClient(idContact,echange);
        return "redirect:/contact/details/client/"+idContact;
    }


}
