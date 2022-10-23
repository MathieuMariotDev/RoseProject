package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchangeRepository extends CrudRepository<Echange, Long> {


    List<Echange> findEchangeByReferenceAndUser(String reference, User user);

    List<Echange> findEchangeByReferenceNotContainsAndUser(String reference, User user);

}
