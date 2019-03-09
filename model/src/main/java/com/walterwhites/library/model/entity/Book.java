package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String languages;
    private Integer number;
    private Integer max_number;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Library> libraries;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Loan> loans;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Reservation> reservations;

    public Book(String title, String author, String language, Integer number) {
        super();
        this.title = title;
        this.author = author;
        this.languages = language;
        this.number = number;
    }

    public void addLibrary(Library library) {
        libraries.add(library);
    }
}