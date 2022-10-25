package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.User;

import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

public class EchangeDto{


    private Long id;

    private LocalDateTime createDate;

    private String statusProspecting;

    private String message;

    private User user;

    private Contact contact;

    String reference;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeBeforeReminder;

    private String timerReminderString;


    public LocalDateTime getTimeBeforeReminder() {
        return timeBeforeReminder;
    }

    public void setTimeBeforeReminder(LocalDateTime timeBeforeReminder) {
        this.timeBeforeReminder = timeBeforeReminder;
    }

    public static EchangeDto from(Echange echange){
        EchangeDto echangeDto = new EchangeDto();
        echangeDto.setContact(echange.getContact());
        echangeDto.setUser(echange.getUser());
        echangeDto.setId(echange.getId());
        echangeDto.setMessage(echange.getMessage());
        echangeDto.setReference(echange.getReference());
        echangeDto.setStatusProspecting(echangeDto.getStatusProspecting());
        echangeDto.setCreateDate(echange.getCreateDate());
        echangeDto.setMessage(echangeDto.getMessage());
        echangeDto.setTimeBeforeReminder(echangeDto.getTimeBeforeReminder());
        return echangeDto;
    }

    public Echange toEchange(){
        Echange echange = new Echange();
        echange.setContact(this.contact);
        echange.setUser(this.user);
        echange.setId(this.id);
        echange.setMessage(this.message);
        echange.setReference(this.reference);
        echange.setStatusProspecting(this.statusProspecting);
        echange.setCreateDate(this.createDate);
        echange.setMessage(this.message);
        return echange;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getStatusProspecting() {
        return statusProspecting;
    }

    public void setStatusProspecting(String statusProspecting) {
        this.statusProspecting = statusProspecting;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public EchangeDto() {
    }
}
