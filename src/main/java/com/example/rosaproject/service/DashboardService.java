package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.model.Status;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.repository.EntrepriseRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private ContactRepository contactRepository;

    private EntrepriseRepository entrepriseRepository;

    private EchangeRepository echangeRepository;

    public DashboardService(ContactRepository contactRepository, EntrepriseRepository entrepriseRepository, EchangeRepository echangeRepository) {
        this.contactRepository = contactRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.echangeRepository = echangeRepository;
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

    public Map<String, Long> entreprisesbyNotesCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.echangeRepository.entreprisesbyNotesCount().stream().collect(Collectors.toMap(obj -> (String)obj[0], obj -> (Long)obj[1]));
    }

    public long prospectsByProspectionAucun() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndStatusProspectingAndIsClientFalse(connectedUser, Status.Aucun.getStatusName());
    }

    public long prospectsByProspectionEnCours() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndStatusProspectingAndIsClientFalse(connectedUser, Status.cours.getStatusName());
    }

    public long prospectsByProspectionARelancer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndStatusProspectingAndIsClientFalse(connectedUser, Status.relancer.getStatusName());
    }

    public long prospectsByProspectionTermine() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByUserAndStatusProspectingAndIsClientFalse(connectedUser, Status.termine.getStatusName());
    }

    /*public Map<String, Long> countContactsByEntreprise() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return this.contactRepository.countContactsByEntrepriseIdAndUser(connectedUser);
    }*/
}
