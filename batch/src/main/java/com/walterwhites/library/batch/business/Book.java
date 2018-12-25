package com.walterwhites.library.batch.business;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor
public class Book implements Iterable {
    private Long id;
    private String title;
    private String author;
    private String language;
    private String state;
    private Date loan_start_date;
    private Date loan_end_date;
    private @Setter(AccessLevel.NONE) Library library;
    private @Setter(AccessLevel.NONE) Client client;

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

    public Library setLibrary(String library) {
        Library lib = new Library();
        lib.setName(library);
        return lib;
    }

    public Client setClient(String client) {
        Client cli = new Client();
        cli.setFirstname(client);
        return cli;
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
