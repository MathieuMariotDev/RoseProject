package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public class CreateUserDto {

    private long id;
    private String password;

    private MultipartFile pictureFile;

    private String picture;

    private String email;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String role;

    private Collection<Contact> contacts;

    private Collection<Echange> echanges;

    private Collection<CreateEntrepriseDto> entreprises;

    private Collection<Evenement> evenements;

    public User toUser(){
        User user = new User();
        user.setEmail(this.email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(this.password));
        user.setRole("ROLE_USER");
        user.setPicture(this.picture);
        return user;
    }

    public CreateUserDto toDto(User user){
        CreateUserDto userDto = new CreateUserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(user.getPassword()));
        userDto.setRole(userDto.getRole());
        userDto.setPicture(user.getPicture());
        return userDto;
    }

    public User toUserModify(User user){
        user.setEmail(this.email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(this.password));
        user.setRole("ROLE_USER");
        user.setPicture(this.picture);
        return user;
    }
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public Collection<CreateEntrepriseDto> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Collection<CreateEntrepriseDto> entreprises) {
        this.entreprises = entreprises;
    }

    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
