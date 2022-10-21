package com.example.rosaproject.controller.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "entreprises", schema = "rosacrm", catalog = "")
public class Entreprise {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "logo")
    private String logo;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "siret")
    private String siret;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "cellPhone")
    private String cellPhone;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Column(name = "urlWebSite")
    private String urlWebSite;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "additionalAddress")
    private String additionalAddress;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "postalCode")
    private String postalCode;
    @Basic
    @Column(name = "typeOfActivity")
    private String typeOfActivity;
    @Basic
    @Column(name = "createDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @OneToMany(mappedBy = "entreprise")
    private Collection<Contact> contactsById;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Entreprise(String logo, String name, String siret, String email, String cellPhone, String phone, String urlWebSite, String address, String additionalAddress, String city, String postalCode, String typeOfActivity, LocalDate createDate, User user) {
        this.logo = logo;
        this.name = name;
        this.siret = siret;
        this.email = email;
        this.cellPhone = cellPhone;
        this.phone = phone;
        this.urlWebSite = urlWebSite;
        this.address = address;
        this.additionalAddress = additionalAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.typeOfActivity = typeOfActivity;
        this.createDate = createDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite) {
        this.urlWebSite = urlWebSite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTypeOfActivity() {
        return typeOfActivity;
    }

    public void setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Entreprise() {
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Collection<Contact> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contact> contactsById) {
        this.contactsById = contactsById;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userById1) {
        this.user = userById1;
    }
}
