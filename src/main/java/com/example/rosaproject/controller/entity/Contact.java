package com.example.rosaproject.controller.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "contact", schema = "rosacrm", catalog = "")
public class Contact {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "firstName")
    private String firstName;
    @Basic
    @Column(name = "cellPhone")
    private String cellPhone;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "createDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    @Basic
    @Column(name = "isClient")
    private boolean isClient=false;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "entreprise_id", referencedColumnName = "id", nullable = false)
    private Entreprise entreprise;
    @OneToMany(mappedBy = "contact")
    private Collection<Echange> echangesById;
    @OneToMany(mappedBy = "contact")
    private Collection<Evenement> evenementsById;

    private String picture;

    public boolean isClient() {
        return isClient;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Contact(String email, String name, String firstName, String cellPhone, String phone, LocalDate createDate, boolean isClient, User user, Entreprise entreprise,String picture) {
        this.email = email;
        this.name = name;
        this.firstName = firstName;
        this.cellPhone = cellPhone;
        this.phone = phone;
        this.createDate = createDate;
        this.isClient = isClient;
        this.user = user;
        this.entreprise = entreprise;
        this.picture = picture;
    }

    public Contact() {
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

    public boolean getIsClient() {
        return isClient;
    }

    public void setIsClient(boolean isClient) {
        this.isClient = isClient;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User userById1) {
        this.user = userById1;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entrepriseById2) {
        this.entreprise = entrepriseById2;
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
}
