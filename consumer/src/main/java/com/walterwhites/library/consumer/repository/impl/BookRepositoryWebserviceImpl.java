package com.walterwhites.library.consumer.repository.impl;

import com.walterwhites.library.consumer.repository.contract.BookRepositoryWebservice;
import library.io.github.walterwhites.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepositoryWebserviceImpl implements BookRepositoryWebservice {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    private static List<Book> books = new LinkedList<>();

    @Override
    public List<Book> findByTitle(String title) {
        books = (List<Book>) operations.query(
                "SELECT * FROM book WHERE title = ?",
                 (rs, rownumber) -> {
                     return getBookData(rs);
                 }, title);
        myLogger.info("Info Log");
        myLogger.info(books.toString());
        return books;
    }

    @Override
    public Book findById(long id) {
        Book book = (Book) operations.queryForObject(
                "SELECT * FROM book WHERE id = ?",
                (rs, rownumber) -> getBookData(rs), id);
        return book;
    }

    private Book getBookData(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setId(rs.getInt("id"));
        b.setAuthor(rs.getString("author"));
        b.setTitle(rs.getString("title"));
        return b;
    }
}