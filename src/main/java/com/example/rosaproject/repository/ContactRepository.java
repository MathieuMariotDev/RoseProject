package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.model.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    // dashboard
    long countContactsByUser(User user);

    long countContactsByUserAndIsClientFalse(User user); // prospects
    long countContactsByUserAndStatusProspectingAndIsClientFalse(User user, String status);

    long countContactsByUserAndIsClientTrue(User user); // clients

    /*@Query("select c.entreprise.name, count(c)" +
            " from Contact c" +
            " group by c.user.id, c.entreprise.id")
    Map<String, Long> countContactsByEntrepriseIdAndUser(User user);*/


    // Retourne la liste de contact qui sont des clients
    List<Contact> findContactByIsClientTrueAndUser(User user);
    // Retourne la liste de contact qui ne sont pas des clients
    List<Contact> findContactByIsClientFalseAndUser(User user);
    // Retourne la liste de contact qui sont des clients trié dans l'ordre ASC

    @Query("SELECT c FROM Contact c where c.user = :user and c.isClient = :isClient")
    List<Contact> findContactByUserWithFilter(User user, Sort sort,boolean isClient);

    @Query("SELECT c FROM Contact c where c.user = :user and c.statusProspecting = :prospectingStatus and c.isClient = :isClient")
    List<Contact> findContactByUserWithFilterAndStatusProspecting(User user ,Sort sort , String prospectingStatus,boolean isClient);



    @Query("SELECT c FROM Contact c where c.user = :user and c.statusProspecting = :prospectingStatus and c.isClient = :isClient AND (LOWER(c.name)  LIKE LOWER(CONCAT('%',:searchValue,'%')) or c.firstName like LOWER(CONCAT('%',:searchValue,'%')))")
    List<Contact> findContactByUserAndNameContainsOrFirstNameContainsStatusProspectingEqualsWithFilter(User user ,Sort sort ,String searchValue, String prospectingStatus,boolean isClient);
    @Query("SELECT c FROM Contact c where c.user = :user and c.isClient = :isClient AND  ( LOWER(c.name)  LIKE LOWER(CONCAT('%',:searchValue,'%')) or c.firstName like LOWER(CONCAT('%',:searchValue,'%')))")
    List<Contact>  findContactByUserAndNameFirstNameContainsNofilter(User user,Sort sort,String searchValue,boolean isClient);


    List<Contact> findContactByIsClientTrueOrderByNameAsc();
    // Retourne la liste de contact qui sont des clients trié dans l'ordre DESC
    List<Contact> findContactByIsClientTrueOrderByNameDesc();
    // Retourne la liste de contact qui ne sont pas des clients trié dans l'ordre ASC
    List<Contact> findContactByIsClientFalseOrderByNameAsc();
    // Retourne la liste de contact qui ne sont pas des clients trié dans l'ordre DESC
    List<Contact> findContactByIsClientFalseOrderByNameDesc();


}
