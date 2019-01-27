package com.walterwhites.library.consumer.repository.jaxb.impl;

import com.walterwhites.library.business.utils.DateUtils;
import library.io.github.walterwhites.Book;
import library.io.github.walterwhites.Language;
import library.io.github.walterwhites.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class BookRepositoryImpl implements BookRepository, BookRepositoryJPA {

    @PersistenceContext
    private EntityManager em;

    private JdbcOperations operations;

    private static List<Book> books = new LinkedList<>();

    @Autowired
    public void BookRepositoryImpl(JdbcOperations jdbcOperations) {
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
        Language language = Language.fromValue(rs.getString("languages"));
        b.setLanguages(language);

        XMLGregorianCalendar endDate = DateUtils.toXmlGregorianCalendar(rs.getDate("loan_end_date"));
        XMLGregorianCalendar startDate = DateUtils.toXmlGregorianCalendar(rs.getDate("loan_start_date"));
        b.setLoanEndDate(endDate);
        b.setLoanStartDate(startDate);

        State state = State.fromValue(rs.getString("state"));
        b.setState(state);
        return b;
    }
}