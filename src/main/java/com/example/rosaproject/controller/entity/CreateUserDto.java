package com.example.rosaproject.controller.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Collection;

public class CreateUserDto {

    private String password;

    private MultipartFile pictureFile;

    private String email;

    private Short role;

    private Collection<Contact> contacts;

    private Collection<Echange> echanges;

    private Collection<Entreprises> entreprises;

    private Collection<Evenement> evenements;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contactsById) {
        this.contacts = contactsById;
    }

    public Collection<Echange> getEchanges() {
        return echanges;
    }

    public void setEchanges(Collection<Echange> echangesById) {
        this.echanges = echangesById;
    }

    public Collection<Entreprises> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Collection<Entreprises> entreprises) {
        this.entreprises = entreprises;
    }

    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }
}
