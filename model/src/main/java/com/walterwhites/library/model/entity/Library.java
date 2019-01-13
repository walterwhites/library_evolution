package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Library")
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Admin> admins;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Book> books;
    private String name;
    private String phoneNumber;
    private String address;
    private String test;
}
