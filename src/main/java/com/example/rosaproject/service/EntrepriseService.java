package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.repository.EntrepriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Entreprise> getAllEntreprises() {
        return (List<Entreprise>) this.entrepriseRepository.findAll();
    }

    /*public Entreprise getSpecificEntreprise(long id, Model model) {
        Optional<Entreprise> entrepriseOptional = this.entrepriseRepository.findById(id);

        if (entrepriseOptional.isPresent()) {
            Entreprise entreprise = entrepriseOptional.get();
            return entreprise;
        } else {
            // TODO: throw exception
        }
        return;
    }*/

    public void createEntreprise(Entreprise createEntreprise) {

        /* pour le edit seulement ?
        Entreprise entreprise = new Entreprise();

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
        entreprise.setCreateDate(createEntreprise.getCreateDate());*/


        entrepriseRepository.save(createEntreprise);
    }
}
