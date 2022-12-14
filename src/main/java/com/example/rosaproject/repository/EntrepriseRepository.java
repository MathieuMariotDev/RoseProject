package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {

    long countEntreprisesByUser(User user);

    Entreprise findByIdAndUser(long id, User user);

    List<Entreprise> findAllByUserOrderByNameAsc(User user);

    List<Entreprise> findEntrepriseByNameContainsAndUserOrCityContainsAndUser(String name, User user1, String city, User user2);
}
