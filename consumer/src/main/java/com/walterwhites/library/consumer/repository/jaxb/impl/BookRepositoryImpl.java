package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.Book;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookRepositoryImpl implements BookRepositoryCustom {

    public BookRepository jpa;

    private JdbcOperations operations;

    private static List<Book> books = new LinkedList<>();

    public void BookRepositoryImpl(BookRepository bookRepository, JdbcOperations jdbcOperations) {
        this.jpa = bookRepository;
        this.operations = jdbcOperations;
    }

    @Override
    public List<Book> findByTitle(String title) {
        books = (List<Book>) operations.query(
                "SELECT * FROM book WHERE title = ?",
                (rs, rownumber) -> {
                    return getBookData(rs);
                }, title);
        return books;
    }

    @Override
    public Book findById(Integer id) {
        Book book = (Book) operations.queryForObject(
                "SELECT * FROM book WHERE id = ?",
                (rs, rownumber) -> getBookData(rs), id);
        return book;
    }

    @Override
    public List<Book> findAllBooks() {
        books = (List<Book>) operations.query(
                "SELECT * FROM book",
                (rs, rownumber) -> {
                    return getBookData(rs);
                });
        return books;
    }

    private Book getBookData(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setId(rs.getInt("id"));
        b.setAuthor(rs.getString("author"));
        b.setTitle(rs.getString("title"));
        return b;
    }
}