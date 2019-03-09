package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Configuration
@EnableAutoConfiguration
@Transactional
public class ClientRepositoryEntityImpl implements ClientEntity {

    @PersistenceContext()
    private EntityManager em;

    @Transactional
    public void refresh(Client client) {
        em.refresh(client);
    }

    @Autowired
    private JdbcOperations operations;

    public Client findByUsername(String username) {
        Client client = (Client) this.operations.queryForObject(
                "SELECT * FROM client WHERE client.username = ?", (rs, rownumber) -> {
                    return getClientData(rs);
                }, username);
        return client;
    }

    public Boolean updateAlertEmail(Client client, Boolean value) {
        client.setAlert_email(value);
        this.em.merge(client);
        return value;
    }

    @Override
    public <S extends Client> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Client> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Client> findById(Long aLong) {
        Client loans = this.em.find(Client.class, aLong);
        return Optional.ofNullable(loans);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public Iterable<Client> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Client entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Client> entities) {

    }

    @Override
    public void deleteAll() {

    }

    private Client getClientData(ResultSet rs) throws SQLException {
        Client c = new Client();
        c.setId(rs.getLong("id"));
        c.setFirstname(rs.getString("firstname"));
        c.setLanguage(rs.getString("language"));
        c.setLastname(rs.getString("lastname"));
        c.setAccountNonExpired(rs.getBoolean("account_non_expired"));
        c.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        c.setCreated_at(rs.getDate("created_at"));
        c.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
        c.setEmail(rs.getString("email"));
        c.setEnabled(rs.getBoolean("enabled"));
        c.setPassword(rs.getString("password"));
        c.setUsername(rs.getString("username"));
        c.setAlert_email(rs.getBoolean("alert_email"));

        return c;
    }
}