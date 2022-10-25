package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.EventDto;
import com.example.rosaproject.repository.EventRepository;
import com.example.rosaproject.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventViewController {

  EventService eventService;

    public EventViewController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event/calendard")
    public String displayCalendar() {
        return "calendar";
    }

    @PostMapping("/event/add/{id}")
    String submitToEventModal(@PathVariable("id") Long idContact, EventDto eventDto){
        eventService.addEvent(idContact,eventDto);
        return "redirect:/event/calendard";
    }

}
