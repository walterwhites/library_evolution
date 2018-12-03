package com.walterwhites.library.model.entity;

import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String author;
    private String language;
    private String state;
    private Date loan_start_date;
    private Date loan_end_date;
    private Library library;
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getLoan_start_date() {
        return loan_start_date;
    }

    public void setLoan_start_date(Date loan_start_date) {
        this.loan_start_date = loan_start_date;
    }

    public Date getLoan_end_date() {
        return loan_end_date;
    }

    public void setLoan_end_date(Date loan_end_date) {
        this.loan_end_date = loan_end_date;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
