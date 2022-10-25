package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Event;
import com.example.rosaproject.controller.entity.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

public class EventDto {


    private Long id;

    private String title;

    private String description;

    private String address;

    private String phone;

    private boolean fullDay=false;

    public boolean isFullDay() {
        return fullDay;
    }

    public void setFullDay(boolean fullDay) {
        this.fullDay = fullDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    private UserDto user;

    private Long idContact;

    private Long idClient;


    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public EventDto(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    private ContactDto contact;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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
        eventDto.setId(event.getId());
        eventDto.setAddress(event.getAddress());
        eventDto.setTitle(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStart(event.getDateTimeStart());
        eventDto.setFinish(event.getDateTimeEnd());
        eventDto.setPhone(event.getPhone());
        eventDto.setContact(ContactDto.from(event.getContactById2()));
        eventDto.setUser(UserDto.from(event.getUsersById1()));
        return eventDto;
    }

    public Event toEvent(){
        Event  event = new Event();
        event.setAddress(this.address);
        event.setName(this.title);
        event.setDescription(this.description);
        event.setDateTimeEnd(this.finish);
        event.setDateTimeStart(this.start);
        event.setPhone(this.phone);
        return event;
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
