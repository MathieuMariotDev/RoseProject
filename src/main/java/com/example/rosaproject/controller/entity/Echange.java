package com.example.rosaproject.controller.entity;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "echange", schema = "rosacrm", catalog = "")
public class Echange {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "createDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createDate;

    @Transient
    private String createDateString;

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    @Basic
    @Column(name = "statusProspecting")
    private String statusProspecting;
    @Basic
    @Column(name = "message")
    private String message;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    private Contact contact;

    String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    private String prospecting;

    public String getProspecting() {
        return prospecting;
    }

    public void setProspecting(String prospecting) {
        this.prospecting = prospecting;
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

    public void setUser(User userById1) {
        this.user = userById1;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contactById2) {
        this.contact = contactById2;
    }

    public Echange() {
    }

    public Echange(LocalDateTime createDate, String statusProspecting, String message, User user, Contact contact,String reference) {
        this.createDate = createDate;
        this.statusProspecting = statusProspecting;
        this.message = message;
        this.user = user;
        this.contact = contact;
        this.reference = reference;
    }
}
