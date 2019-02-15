package com.walterwhites.library.consumer.repository.jaxb.impl;

import com.walterwhites.library.business.utils.DateUtils;
import com.walterwhites.library.consumer.repository.entity.BookRepositoryEntityImpl;
import library.io.github.walterwhites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class BookRepositoryImpl implements BookRepository, BookRepositoryJPA {

    @PersistenceContext
    private EntityManager em;

    private JdbcOperations operations;

    private BookRepositoryEntityImpl bookRepositoryEntity;

    private static List<Book> books = new LinkedList<>();

    @Autowired
    public void BookRepositoryImpl(JdbcOperations jdbcOperations, BookRepositoryEntityImpl bookRepositoryEntity) {
        this.bookRepositoryEntity = bookRepositoryEntity;
        this.operations = jdbcOperations;
    }

    @Override
    public List<Book> findByTitle(String titleOfBook) {
        books = (List<Book>) operations.query(
                "SELECT\n" +
                        "    book.* AS book,\n" +
                        "    book_loans.loans_id AS loan_id,\n" +
                        "    library_books.library_id AS library_id,\n" +
                        "    loan.end_date AS loan_end_date,\n" +
                        "    loan.renewed AS loan_renewed,\n" +
                        "    loan.start_date AS loan_start_date,\n" +
                        "    loan.updated_date AS loan_updated_date,\n" +
                        "    loan.client_id AS loan_client_id,\n" +
                        "    loan.state AS loan_state,\n" +
                        "    library.address AS library_address,\n" +
                        "    library.name AS library_name,\n" +
                        "    library.phone_number AS library_phone_number\n" +
                        "FROM\n" +
                        "    book\n" +
                        "    LEFT JOIN book_loans ON book.id = book_loans.book_id\n" +
                        "    LEFT JOIN loan ON book_loans.loans_id = loan.id\n" +
                        "    LEFT JOIN library_books ON book.id = library_books.books_id\n" +
                        "    LEFT JOIN library ON library_books.library_id = library.id\n" +
                        "WHERE\n" +
                        "    book.title = ?" +
                        "ORDER BY loan_id DESC LIMIT 1",
                (rs, rownumber) -> {
                    return getBookData(rs);
                }, titleOfBook);
        return books;
    }

    @Override
    public Book findById(Long idOfBook) {
        Book book = (Book) operations.queryForObject(
                "SELECT\n" +
                        "    book.* AS book,\n" +
                        "    book_loans.loans_id AS loan_id,\n" +
                        "    library_books.library_id AS library_id,\n" +
                        "    loan.end_date AS loan_end_date,\n" +
                        "    loan.renewed AS loan_renewed,\n" +
                        "    loan.start_date AS loan_start_date,\n" +
                        "    loan.updated_date AS loan_updated_date,\n" +
                        "    loan.client_id AS loan_client_id,\n" +
                        "    loan.state AS loan_state,\n" +
                        "    library.address AS library_address,\n" +
                        "    library.name AS library_name,\n" +
                        "    library.phone_number AS library_phone_number\n" +
                        "FROM\n" +
                        "    book\n" +
                        "    LEFT JOIN book_loans ON book.id = book_loans.book_id\n" +
                        "    LEFT JOIN loan ON book_loans.loans_id = loan.id\n" +
                        "    LEFT JOIN library_books ON book.id = library_books.books_id\n" +
                        "    LEFT JOIN library ON library_books.library_id = library.id\n" +
                        "WHERE\n" +
                        "    book.id = ?" +
                        "ORDER BY loan_id DESC LIMIT 1",
                (rs, rownumber) -> getBookData(rs), idOfBook);
        return book;
    }

    @Override
    public List<Book> findAllBooks() {
        books = (List<Book>) operations.query(
                "SELECT\n" +
                        "    book.* AS book,\n" +
                        "    library_books.library_id AS library_id,\n" +
                        "    library.address AS library_address,\n" +
                        "    library.name AS library_name,\n" +
                        "    library.phone_number AS library_phone_number\n" +
                        "FROM\n" +
                        "    book\n" +
                        "    LEFT JOIN library_books ON book.id = library_books.books_id\n" +
                        "    LEFT JOIN library ON library_books.library_id = library.id\n",
                (rs, rownumber) -> {
                    return getAllBookData(rs);
                });
        return books;
    }

    @Override
    public List<Book> findAllBooksFromClient(String username) {
        books = (List<Book>) operations.query(
                "SELECT\n" +
                        "    book.* AS book,\n" +
                        "    book_loans.loans_id AS loan_id,\n" +
                        "    library_books.library_id AS library_id,\n" +
                        "    loan.end_date AS loan_end_date,\n" +
                        "    loan.renewed AS loan_renewed,\n" +
                        "    loan.start_date AS loan_start_date,\n" +
                        "    loan.updated_date AS loan_updated_date,\n" +
                        "    loan.client_id AS loan_client_id,\n" +
                        "    loan.state AS loan_state,\n" +
                        "    library.address AS library_address,\n" +
                        "    library.name AS library_name,\n" +
                        "    library.phone_number AS library_phone_number\n" +
                        "FROM\n" +
                        "    book\n" +
                        "    LEFT JOIN book_loans ON book.id = book_loans.book_id\n" +
                        "    LEFT JOIN loan ON book_loans.loans_id = loan.id\n" +
                        "    LEFT JOIN library_books ON book.id = library_books.books_id\n" +
                        "    LEFT JOIN library ON library_books.library_id = library.id\n" +
                        "    LEFT JOIN client ON loan.client_id = client.id\n" +
                        "   WHERE client.username = ?",
                (rs, rownumber) -> {
                    return getBookData(rs);
                }, username);
        return books;
    }

    private Book getAllBookData(ResultSet rs) throws SQLException {
        Book b = new Book();
        Libraries library = new Libraries();

        library.setId(rs.getLong("library_id"));
        library.setAddress(rs.getString("library_address"));
        library.setName(rs.getString("library_name"));
        library.setPhoneNumber(rs.getString("library_phone_number"));

        b.setId(rs.getLong("id"));
        b.setAuthor(rs.getString("author"));
        b.setTitle(rs.getString("title"));
        Language language = Language.fromValue(rs.getString("languages"));
        b.setLanguages(language);

        b.setNumber(rs.getInt("number"));

        b.setLibraries(library);
        return b;
    }

    private Book getBookData(ResultSet rs) throws SQLException {
        Book b = new Book();
        Loans loan = new Loans();
        Libraries library = new Libraries();

        library.setId(rs.getLong("library_id"));
        library.setAddress(rs.getString("library_address"));
        library.setName(rs.getString("library_name"));
        library.setPhoneNumber(rs.getString("library_phone_number"));

        b.setId(rs.getLong("id"));
        b.setAuthor(rs.getString("author"));
        b.setTitle(rs.getString("title"));
        Language language = Language.fromValue(rs.getString("languages"));
        b.setLanguages(language);

        b.setNumber(rs.getInt("number"));

        State state = State.fromValue(rs.getString("loan_state"));
        loan.setState(state);
        loan.setId(rs.getLong("loan_id"));
        XMLGregorianCalendar end_date = DateUtils.toXmlGregorianCalendar(rs.getDate("loan_end_date"));
        loan.setEndDate(end_date);
        XMLGregorianCalendar start_date = DateUtils.toXmlGregorianCalendar(rs.getDate("loan_start_date"));
        loan.setStartDate(start_date);
        loan.setRenewed(rs.getBoolean("loan_renewed"));
        XMLGregorianCalendar updated_date = DateUtils.toXmlGregorianCalendar(rs.getDate("loan_updated_date"));
        loan.setUpdatedDate(updated_date);

        b.setLoans(loan);
        b.setLibraries(library);
        return b;
    }
}

/**
 SELECT
 book.* AS book,
 loan_books.loan_id AS loan_id,
 library_books.library_id AS library_id,
 loan.end_date AS loan_end_date,
 loan.renewed AS loan_renewed,
 loan.start_date AS loan_start_date,
 loan.state AS loan_state,
 loan.updated_date AS loan_updated_date,
 loan.client_id AS loan_client_id,
 library.address AS library_address,
 library.name AS library_name,
 library.phone_number AS library_phone_number
 FROM
 book
 LEFT JOIN loan_books ON book.id = loan_books.books_id
 LEFT JOIN loan ON loan_books.loan_id = loan.id
 LEFT JOIN library_books ON book.id = library_books.books_id
 LEFT JOIN library ON library_books.library_id = library.id
 WHERE
 book.id = 1

 **/