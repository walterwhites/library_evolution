package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private String language;
    private String state;
    private Date loan_start_date;
    private Date loan_end_date;
    private Library library;
    private Client client;

    public Book(String title, String author, String language, String state, Date loan_start_date, Date loan_end_date, Library library, Client client) {
        super();
        this.title = title;
        this.author = author;
        this.language = language;
        this.state = state;
        this.loan_start_date = loan_start_date;
        this.loan_end_date = loan_end_date;
        this.library = library;
        this.client = client;
    }
}
