package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class EchangeService {

    EchangeRepository echangeRepository;

    ContactService contactService;

    ContactRepository contactRepository;

    public EchangeService(EchangeRepository echangeRepository, ContactService contactService, ContactRepository contactRepository) {
        this.echangeRepository = echangeRepository;
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    public Echange getEchangeById(Long id){
        return echangeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Echange with id:"+id+" doest not exist"));
    }

    public void deleteEchange(Long id){
        Echange echange = getEchangeById(id);
        echangeRepository.delete(echange);
    }

    public void updateEchange(Long id,Echange echangeFromView){
        Echange echange = getEchangeById(id);
        echange.setMessage(echangeFromView.getMessage());
        echange.setStatusProspecting(echangeFromView.getStatusProspecting());
        echangeRepository.save(echange);
    }

    public void addEchange(Long idContact,Echange echange){
        Contact contact = contactService.findContactById(idContact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        echange.setUser(customUser.getUser());
        echange.setContact(contact);
        echange.setCreateDate(LocalDateTime.now());
        echange.setId(null); // ? Use dto create
        if (contact.getEchangesById().get(contact.getEchangesById().size()-1).getStatusProspecting().equals("Aucun")){
            echange.setStatusProspecting("En cours");
        }else{
            echange.setStatusProspecting(contact.getEchangesById().get(contact.getEchangesById().size()-1).getStatusProspecting());
        }
        echangeRepository.save(echange);
        contact.setStatusProspecting("En cours de prospection");
        contactRepository.save(contact);
    }

    public void addResumeTimer(Long idEchange, Echange echange){
        Echange echangeToUpdate = getEchangeById(idEchange);
        echangeToUpdate.setTimeBeforeReminder(echange.getTimeBeforeReminder());
        echangeRepository.save(echangeToUpdate);
    }

    public void addResumeNote(Long idContact,Echange echange){
        Contact contact = contactService.findContactById(idContact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        echange.setUser(customUser.getUser());
        echange.setContact(contact);
        echange.setId(null);
        echange.setStatusProspecting("A relancer");
        echange.setCreateDate(LocalDateTime.now());
        contact.getEchangesById().add(echange);
        echangeRepository.save(echange);
        contact.setStatusProspecting("A relancer");
        contactRepository.save(contact);
    }



}
