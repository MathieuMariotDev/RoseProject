package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {

    List<Entreprise> findAllByUserOrderByNameAsc(User user);
}
