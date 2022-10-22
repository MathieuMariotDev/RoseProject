package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

public class CreateContactDto {


    private Long id;

    private String email;

    private String name;

    private String firstName;

    private String cellPhone;

    private String phone;

    private LocalDate createDate;

    private boolean isClient=false;

    private User user;

    private Entreprise entreprise;

    private Collection<Echange> echangesById;

    private Collection<Evenement> evenementsById;

    private String picture;

    private MultipartFile file;

    private String statusProspecting;

    public String getStatusProspecting() {
        return statusProspecting;
    }

    public void setStatusProspecting(String statusProspecting) {
        this.statusProspecting = statusProspecting;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public CreateContactDto() {
    }

    public Contact toContact(){
        Contact contact = new Contact();
        contact.setFirstName(this.firstName);
        contact.setName(this.name);
        contact.setEmail(this.email);
        contact.setPhone(this.phone);
        contact.setPicture(this.picture);
        contact.setIsClient(this.isClient);
        contact.setUser(this.user);
        contact.setCreateDate(this.createDate);
        contact.setEntreprise(this.entreprise);
        return contact;
    }

    public static CreateContactDto fromContact(Contact contact){
        CreateContactDto createContactDto = new CreateContactDto();
        createContactDto.setFirstName(contact.getFirstName());
        createContactDto.setName(contact.getName());
        createContactDto.setEmail(contact.getEmail());
        createContactDto.setPhone(contact.getPhone());
        createContactDto.setCellPhone(contact.getCellPhone());
        createContactDto.setPicture(contact.getPicture());
        createContactDto.setClient(contact.getIsClient());
        createContactDto.setUser(contact.getUser());
        createContactDto.setCreateDate(contact.getCreateDate());
        createContactDto.setEntreprise(contact.getEntreprise());
        createContactDto.setId(contact.getId());
        return createContactDto;
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

    public Collection<Echange> getEchangesById() {
        return echangesById;
    }

    public void setEchangesById(Collection<Echange> echangesById) {
        this.echangesById = echangesById;
    }

    public Collection<Evenement> getEvenementsById() {
        return evenementsById;
    }

    public void setEvenementsById(Collection<Evenement> evenementsById) {
        this.evenementsById = evenementsById;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
