package com.walterwhites.library.model.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.List;

public class Loan {

    private int id;
    private Date start_date;
    private Date end_date;
    private Client client;
    private Bool renewed;
    private List<Book> books;
    private String state;
    private Date updated_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bool getRenewed() {
        return renewed;
    }

    public void setRenewed(Bool renewed) {
        this.renewed = renewed;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
}
