package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Evenement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends CrudRepository<Evenement,Long> {
}
