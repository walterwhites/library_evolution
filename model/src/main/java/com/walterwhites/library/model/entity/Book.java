package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private String state;
    private Date obtaining_date;
    private Integer number;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Library> libraries;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Loan> loans;

    public Book(String title, String author, String language, String state, Date obtaining_date, Integer number) {
        super();
        this.title = title;
        this.author = author;
        this.languages = language;
        this.state = state;
        this.obtaining_date = obtaining_date;
        this.number = number;
    }

    public void addLibrary(Library library) {
        libraries.add(library);
    }
}