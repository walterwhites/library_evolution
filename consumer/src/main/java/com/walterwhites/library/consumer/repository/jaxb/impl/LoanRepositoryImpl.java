package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.loans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
@EnableAutoConfiguration
@ComponentScan
@Configuration
@Transactional
public class LoanRepositoryImpl implements LoanRepository, LoanRepositoryJPA {

    private static List<Loans> loans = new LinkedList<>();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Override
    public List<Loans> findAllNotReturnedBook() {
        loans = (List<Loans>) operations.query(
                "SELECT loan.id, client.email, client.firstname, client.lastname, book.title " +
                        "FROM loan LEFT JOIN client_loans ON loan.id = client_loans.loans_id " +
                        "LEFT JOIN client ON client_loans.client_id = client.id " +
                        "LEFT JOIN book " +
                        "ON loan.book_id = book.id " +
                        "WHERE loan.state = 'borrowed' AND " +
                        "DATE(loan.end_date) < DATE(NOW())",
                (rs, rownumber) -> {
                    return getLoanData(rs);
                });
        return loans;
    }

    private Loans getLoanData(ResultSet rs) throws SQLException {

        Book book = new Book();
        Loans loan = new Loans();
        Client client = new Client();

        loan.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        client.setFirstname(rs.getString("firstname"));
        client.setLastname(rs.getString("lastname"));
        client.setEmail(rs.getString("email"));

        return loan;
    }
}