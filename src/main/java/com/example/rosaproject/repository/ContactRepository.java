package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.User;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    // Retourne la liste de contact qui sont des clients
    List<Contact> findContactByIsClientTrue();
    // Retourne la liste de contact qui ne sont pas des clients
    List<Contact> findContactByIsClientFalse();
    // Retourne la liste de contact qui sont des clients trié dans l'ordre ASC

    @Query("SELECT c FROM Contact c where c.user = :user")
    List<Contact> findContactFilter(User user, Sort sort);



    List<Contact> findContactByIsClientTrueOrderByNameAsc();
    // Retourne la liste de contact qui sont des clients trié dans l'ordre DESC
    List<Contact> findContactByIsClientTrueOrderByNameDesc();
    // Retourne la liste de contact qui ne sont pas des clients trié dans l'ordre ASC
    List<Contact> findContactByIsClientFalseOrderByNameAsc();
    // Retourne la liste de contact qui ne sont pas des clients trié dans l'ordre DESC
    List<Contact> findContactByIsClientFalseOrderByNameDesc();

}
