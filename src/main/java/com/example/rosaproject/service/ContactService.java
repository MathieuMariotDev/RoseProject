package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService {

    ContactRepository contactRepository;

    UserService userService;

    public ContactService(ContactRepository contactRepository, UserService userService) {
        this.contactRepository = contactRepository;
        this.userService = userService;
    }

    public List<Contact> getAllProspect() {
        return contactRepository.findContactByIsClientFalse();
    }

    public List<Contact> getAllClient() {
        return contactRepository.findContactByIsClientTrue();
    }

    public void addProspect(Contact contact){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        contact.setUser(user);
        contact.setCreateDate(LocalDate.now());
        contactRepository.save(contact);
    }

    public void addClient(Contact contact){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        contact.setUser(customUser.getUser());
        contact.setIsClient(true);
        contactRepository.save(contact);
    }

    public Contact findContactById(Long id){
        return contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Contact with id:"+id+" doest not exist"));
    }

    public void deleteContact(Long id){
        Contact contact = findContactById(id);
        contactRepository.delete(contact);
    }

}
