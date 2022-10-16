package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.repository.EntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Entreprise> getAllEntreprises() {
        return (List<Entreprise>) this.entrepriseRepository.findAll();
    }
}
