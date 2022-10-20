package com.example.rosaproject.controller.entity;

import javax.persistence.*;
import java.sql.Date;
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
    private LocalDateTime createDate;
    @Basic
    @Column(name = "statusProspecting")
    private String statusProspecting;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "TimeBeforeReminder")
    private Date timeBeforeReminder;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    private Contact contact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getTimeBeforeReminder() {
        return timeBeforeReminder;
    }

    public void setTimeBeforeReminder(Date timeBeforeReminder) {
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
}
