package com.walterwhites.library.consumer.repository.impl;

import com.walterwhites.library.consumer.jaxb.java.Book;
import com.walterwhites.library.consumer.repository.contract.BookRepositoryWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class BookRepositoryWebserviceImpl implements BookRepositoryWebservice {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    private static final List<Book> books = new LinkedList<>();

    @Override
    public List<Book> findByTitle(String title) {
         List<Book> request = (List<Book>) operations.queryForObject(
                "SELECT * FROM book WHERE title = ?",
                Book.class,
                title);
         books.addAll(request);
         return books;
    }

    @Override
    public Book findById(long id) {
        Book request = (Book) operations.queryForObject(
                "SELECT * FROM book WHERE id = ?",
                Book.class,
                id);
        return request;
    }
}