package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateEntrepriseDto;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.exception.EntrepriseNotFoundException;
import com.example.rosaproject.repository.EntrepriseRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Entreprise> getAllEntreprises() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        return (List<Entreprise>) this.entrepriseRepository.findAllByUserOrderByNameAsc(connectedUser);
    }

    public Entreprise getSpecificEntreprise(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();

        Optional<Entreprise> entrepriseOptional = Optional.ofNullable(this.entrepriseRepository.findByIdAndUser(id, connectedUser));

        if (entrepriseOptional.isPresent()) {
            Entreprise entreprise = entrepriseOptional.get();
            return entreprise;
        } else {
            throw new EntrepriseNotFoundException(id);
        }
    }

    public void createEntreprise(CreateEntrepriseDto createEntreprise) {

       // User user = new User();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
/* // DON T DELETE USE FOR EDIT C/C
        entreprise.setLogo(createEntreprise.getLogo());
        entreprise.setName(createEntreprise.getName());
        entreprise.setSiret(createEntreprise.getSiret());
        entreprise.setEmail((createEntreprise.getEmail()));
        entreprise.setCellPhone(createEntreprise.getCellPhone());
        entreprise.setPhone(createEntreprise.getPhone());
        entreprise.setUrlWebSite(createEntreprise.getUrlWebSite());
        entreprise.setAddress(createEntreprise.getAddress());
        entreprise.setAdditionalAddress(createEntreprise.getAdditionalAddress());
        entreprise.setCity(createEntreprise.getCity());
        entreprise.setPostalCode(createEntreprise.getPostalCode());
        entreprise.setTypeOfActivity(createEntreprise.getTypeOfActivity());
        entreprise.setCreateDate(createEntreprise.getCreateDate());
*/
        createEntreprise.setUser(connectedUser);
        createEntreprise.setUser(customUser.getUser());
        entrepriseRepository.save(createEntreprise.dtoCreateEntrepriseToEntreprise());
    }
}
