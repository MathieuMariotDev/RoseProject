package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Event;
import com.example.rosaproject.controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByUser(User user);
    Event save(Event event);
    void delete(Event event);


    /* Note these two methods do the same thing.  The @Repository annotation handles both*/

    /* This one uses a JPQL */
    //List<Event> findByDateTimeStartGreaterThanEqualAndDateTimeEndLessThanEqual(LocalDateTime start, LocalDateTime end);



    /* This one uses an @Query */
    @Query("select b from Event b where(b.dateTimeStart >= :start and b.dateTimeEnd <= :Dateend)  and b.user = :user")
     List<Event> findByDateBetween(LocalDateTime start, LocalDateTime Dateend,User user);



}
