package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date created_date;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book book;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Client client;
    @OneToOne(cascade = {CascadeType.ALL})
    private Notification notification;
    private String state;
}