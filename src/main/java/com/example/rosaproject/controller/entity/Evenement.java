package com.example.rosaproject.controller.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evenement", schema = "rosacrm", catalog = "")
public class Evenement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "dateTimeStart")
    private LocalDateTime dateTimeStart;
    @Basic
    @Column(name = "dateTimeEnd")
    private LocalDateTime dateTimeEnd;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    private Contact contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }


    public User getUsersById1() {
        return user;
    }

    public void setUsersById1(User userById1) {
        this.user = userById1;
    }

    public Contact getContactById2() {
        return contact;
    }

    public void setContactById2(Contact contactById2) {
        this.contact = contactById2;
    }
}
