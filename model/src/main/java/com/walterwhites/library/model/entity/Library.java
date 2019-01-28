package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Library")
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Admin> admins;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Book> books;
    private String name;
    private String phoneNumber;
    private String address;
}
