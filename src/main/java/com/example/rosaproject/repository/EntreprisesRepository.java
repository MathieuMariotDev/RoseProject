package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Entreprises;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntreprisesRepository extends CrudRepository<Entreprises, Long> {
}
