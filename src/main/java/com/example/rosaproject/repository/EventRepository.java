package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event,Long> {
}
