package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.Event;

import javax.persistence.*;
import java.time.LocalDateTime;

public class EventDto {


    private Long id;

    private String title;

    private String description;

    private String address;

    private LocalDateTime start;


    private LocalDateTime finish;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public static EventDto from(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setAddress(event.getAddress());
        eventDto.setTitle(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStart(event.getDateTimeEnd());
        eventDto.setFinish(event.getDateTimeStart());
        return eventDto;
    }

    public EventDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
