package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private Date loan_start_date;
    private Date loan_end_date;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Library> libraries;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Client> clients;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Loan> loans;

    public Book(String title, String author, String language, String state, Date loan_start_date, Date loan_end_date) {
        super();
        this.title = title;
        this.author = author;
        this.languages = language;
        this.state = state;
        this.loan_start_date = loan_start_date;
        this.loan_end_date = loan_end_date;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addLibrary(Library library) {
        libraries.add(library);
    }
}