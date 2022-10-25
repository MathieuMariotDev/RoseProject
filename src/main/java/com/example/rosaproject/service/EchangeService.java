package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.EchangeDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.model.Status;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        echange.setReference("Prospecting"+contact.getEntreprise().getName());
        if (contact.getEchangesById().size() - 1 == -1){
            echange.setStatusProspecting(Status.cours.getStatusName());
        }else{
            echange.setStatusProspecting(contact.getEchangesById().get(contact.getEchangesById().size()-1).getStatusProspecting());
        }
        echangeRepository.save(echange);
        contact.setStatusProspecting(Status.cours.getStatusName());
        contactRepository.save(contact);
    }

    public void addResumeTimer(Long idContact, EchangeDto echangeDto){
        Contact contact = contactService.findContactById(idContact);
        contact.setTimeBeforeReminder(echangeDto.getTimeBeforeReminder());
        contactRepository.save(contact);
    }

    public void addResumeNote(Long idContact,Echange echange){
        Contact contact = contactService.findContactById(idContact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        echange.setUser(customUser.getUser());
        echange.setContact(contact);
        echange.setId(null);
        echange.setStatusProspecting(Status.relancer.getStatusName());
        echange.setCreateDate(LocalDateTime.now());
        echange.setReference("Prospecting"+contact.getEntreprise().getName());
        contact.getEchangesById().add(echange);
        echangeRepository.save(echange);
        contact.setStatusProspecting(Status.relancer.getStatusName());
        contactRepository.save(contact);
    }

    public List<Echange> findClientEchange(String reference){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        return echangeRepository.findEchangeByReferenceAndUser(reference,customUser.getUser());
    }
    public List<Echange> findOldEchange(String reference){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        return echangeRepository.findEchangeByReferenceNotContainsAndUser(reference,customUser.getUser());
    }

    public void addEchangeForClient(Echange echange,Long idContact){
        Contact contact = contactService.findContactById(idContact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        echange.setUser(customUser.getUser());
        echange.setContact(contact);
        echange.setCreateDate(LocalDateTime.now());
        echange.setId(null); // ? Use dto create
        echange.setReference("Client"+contact.getEntreprise().getName());
        echangeRepository.save(echange);
        contactRepository.save(contact);
    }

    public void addContactTimerForClient(Long idContact, EchangeDto echangeDto){
        Contact contact = contactService.findContactById(idContact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        echangeDto.setUser(customUser.getUser());
        echangeDto.setContact(contact);
        echangeDto.setCreateDate(LocalDateTime.now());
        echangeDto.setId(null); // ? Use dto create
        echangeDto.setReference("Client"+contact.getEntreprise().getName());
        contact.setTimeBeforeReminder(echangeDto.getTimeBeforeReminder());
        echangeRepository.save(echangeDto.toEchange());
        contactRepository.save(contact);
    }


}
