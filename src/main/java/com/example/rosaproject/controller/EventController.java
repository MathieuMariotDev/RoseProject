package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.EventDto;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.Event;
import com.example.rosaproject.exception.BadDateFormatException;
import com.example.rosaproject.repository.EventRepository;
import com.example.rosaproject.security.CustomUserDetails;
import com.example.rosaproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {


    private EventRepository eventRespository;

    private EventService eventService;

    public EventController(EventRepository eventRespository, EventService eventService) {
        this.eventRespository = eventRespository;
        this.eventService = eventService;
    }

    @RequestMapping(value="/allevents", method=RequestMethod.GET)
    public ResponseEntity<List<EventDto>> allEvents() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        List<Event> eventList = eventRespository.findAllByUser(customUser.getUser());
        List<EventDto> eventDtoList = eventList.stream().map(event -> EventDto.from(event)).collect(Collectors.toList());
        return ResponseEntity.accepted().body(eventDtoList);
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<EventDto> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        List<Event> eventList = eventRespository.findByDateBetween(startDateTime, endDateTime,customUser.getUser());
        List<EventDto> eventDtoList = eventList.stream().map(event -> EventDto.from(event)).collect(Collectors.toList());
        return eventDtoList;
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) {
        Event created = eventRespository.save(event);
        return created;
    }

    @RequestMapping(value="/event", method=RequestMethod.PATCH)
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto event) {
        Event eventUpdate  = eventService.updateEvent(event);
        return ResponseEntity.ok(EventDto.from(eventUpdate));
    }

    @RequestMapping(value="/event", method=RequestMethod.DELETE)
    public ResponseEntity<Event> removeEvent(@RequestBody Event event){
    eventService.removeEvent(event.getId());
    return ResponseEntity.ok(event);
    }

}
