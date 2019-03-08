package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.client.Client;
import library.io.github.walterwhites.client.Loans;
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
public class ClientsRepositoryImpl implements ClientRepository, ClientRepositoryJPA {

    private static List<Loans> clients = new LinkedList<>();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Override
    public Client findById(Long idOFClient) {
        Client client = (Client) operations.queryForObject(
                "SELECT * FROM client\n" +
                        "WHERE\n" +
                        "    client.id = ?",
                (rs, rownumber) -> getClientData(rs), idOFClient);
        return client;
    }

    private Client getClientData(ResultSet rs) throws SQLException {

        Client client = new Client();
        client.setFirstname(rs.getString("firstname"));
        client.setLastname(rs.getString("lastname"));
        client.setEmail(rs.getString("email"));
        client.setAlertEmail(rs.getBoolean("alert_email"));

        return client;
    }
}