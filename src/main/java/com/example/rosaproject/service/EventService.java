package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.EventDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Event;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.EventRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class EventService {

    EventRepository eventRepository;

    ContactService contactService;

    UserService userService;

    public EventService(EventRepository eventRepository, ContactService contactService, UserService userService) {
        this.eventRepository = eventRepository;
        this.contactService = contactService;
        this.userService = userService;
    }

    public void addEvent(Long idContact, EventDto eventDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();


        if (eventDto.isFullDay()){
            LocalDateTime localDateTime = eventDto.getStart();
            localDateTime = localDateTime.withHour(23).withMinute(59).withSecond(59);
            eventDto.setFinish(localDateTime);
        }
        Event event =  eventDto.toEvent();
        event.setContactById2(contactService.findContactById(idContact));
        event.setUsersById1(customUser.getUser());
        eventRepository.save(eventDto.toEvent());
    }

    public Event findEventById(Long id){
        return  eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Event with id:"+id+" doest not exist"));
    }


    public void removeEvent(Long idEvent){
        Event event = findEventById(idEvent);
        eventRepository.delete(event);
    }

    public Event updateEvent(EventDto eventDto){
        User user = userService.findById(eventDto.getIdClient());
        Contact contact = contactService.findContactById(eventDto.getIdContact());
        Event event = findEventById(eventDto.getId());
        event.setName(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setDateTimeStart(eventDto.getStart());
        event.setDateTimeEnd(eventDto.getFinish());
        event.setContactById2(contact);
        event.setUsersById1(user);

        eventRepository.save(event);
        return event;
    }


}
