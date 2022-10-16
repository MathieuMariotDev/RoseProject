package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Entreprise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {
}
