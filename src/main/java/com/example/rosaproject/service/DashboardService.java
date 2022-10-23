package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EntrepriseRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private ContactRepository contactRepository;

    private EntrepriseRepository entrepriseRepository;

    public DashboardService(ContactRepository contactRepository, EntrepriseRepository entrepriseRepository) {
        this.contactRepository = contactRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    public long countAllContacts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUser(connectedUser);
    }

    public long countAllProspects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndIsClientFalse(connectedUser);
    }

    public long countAllClients() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndIsClientTrue(connectedUser);
    }

    public long countAllEntreprises() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.entrepriseRepository.countEntreprisesByUser(connectedUser);
    }
}
