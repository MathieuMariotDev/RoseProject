package com.example.rosaproject.controller.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "rosacrm", catalog = "")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "role")
    private Short role;
    @OneToMany(mappedBy = "user")
    private Collection<Contact> contacts;
    @OneToMany(mappedBy = "user")
    private Collection<Echange> echanges;
    @OneToMany(mappedBy = "user")
    private Collection<Entreprise> entreprises;
    @OneToMany(mappedBy = "user")
    private Collection<Evenement> evenements;

    public User() {
    }

    public User(String password, String picture, String email, Short role) {
        this.password = password;
        this.picture = picture;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Collection<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Collection<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }
}
