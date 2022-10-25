package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDto {


    private Long id;

    private String email;

    private String name;

    private String firstName;

    private String cellPhone;

    private String phone;

    private LocalDate createDate;

    private String statusProspecting;

    private boolean isClient=false;


    private User user;

    private Entreprise entreprise;

    private List<Echange> echangesById = new ArrayList<>();


    private List<Event> evenementsById  = new ArrayList<>();





    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public List<Echange> getEchangesById() {
        return echangesById;
    }

    public void setEchangesById(List<Echange> echangesById) {
        this.echangesById = echangesById;
    }

    public List<Event> getEvenementsById() {
        return evenementsById;
    }

    public void setEvenementsById(List<Event> evenementsById) {
        this.evenementsById = evenementsById;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getStatusProspecting() {
        return statusProspecting;
    }

    public void setStatusProspecting(String statusProspecting) {
        this.statusProspecting = statusProspecting;
    }

    public ContactDto() {
    }

    public static ContactDto from(Contact contact){
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setFirstName(contact.getFirstName());
        return contactDto;
    }




}
