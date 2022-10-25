package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EchangeRepository extends CrudRepository<Echange, Long> {

    @Query("select e.contact.entreprise.name, count(e)" +
            " from Echange e" +
            " where e.user = :user" +
            " group by e.contact.entreprise.id"
            )
    List<Object[]> entreprisesbyUserAndNotes(User user);

    List<Echange> findEchangeByReferenceAndUser(String reference, User user);

    List<Echange> findEchangeByReferenceNotContainsAndUser(String reference, User user);

}
