package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.AbstractUser;
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
import java.util.Optional;

@Repository
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class ClientRepositoryImpl implements AbstractUserEntity {

    @PersistenceContext()
    private EntityManager em;

    @Transactional
    public void refresh(Client client) {
        em.refresh(client);
    }

    @Autowired
    private JdbcOperations operations;

    public Client findByUsername(String username) {
        return null;
    }


    @Override
    public <S extends AbstractUser> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AbstractUser> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AbstractUser> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<AbstractUser> findAll() {
        return null;
    }

    @Override
    public Iterable<AbstractUser> findAllById(Iterable<Long> longs) {
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
    public void delete(AbstractUser entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends AbstractUser> entities) {

    }

    @Override
    public void deleteAll() {

    }
}