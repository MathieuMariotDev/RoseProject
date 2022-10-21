package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.repository.UserRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EchangeService {

    EchangeRepository echangeRepository;

    ContactService contactService;

    public EchangeService(EchangeRepository echangeRepository, ContactService contactService) {
        this.echangeRepository = echangeRepository;
        this.contactService = contactService;
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
        echangeRepository.save(echange);
    }



}
