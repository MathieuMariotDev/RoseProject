package com.example.rosaproject.controller.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
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
    private LocalDate createDate;
    @Basic
    @Column(name = "statusProspecting")
    private String statusProspecting;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "TimeBeforeReminder")
    private LocalDateTime timeBeforeReminder;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    private Contact contact;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeBeforeReminder() {
        return timeBeforeReminder;
    }

    public void setTimeBeforeReminder(LocalDateTime timeBeforeReminder) {
        this.timeBeforeReminder = timeBeforeReminder;
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

    public Echange(LocalDate createDate, String statusProspecting, String message, User user, Contact contact) {
        this.createDate = createDate;
        this.statusProspecting = statusProspecting;
        this.message = message;
        this.user = user;
        this.contact = contact;
    }
}
