package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.AbstractUser;
import com.walterwhites.library.model.entity.Admin;
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
@EnableTransactionManagement
public class AdminRepositoryImpl implements AbstractUserEntity {

    @PersistenceContext()
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Transactional
    public void refresh(Admin admin) {
        em.refresh(admin);
    }

    public Admin findByUsername(String username) {
        Admin admin = (Admin) this.operations.queryForObject(
                "SELECT * FROM admins WHERE admins.username = ?", (rs, rownumber) -> {
                    return getAdminData(rs);
                }, username);
        return admin;
    }


    @Override
    public <S extends AbstractUser> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AbstractUser> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(admin -> this.em.persist(admin));
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

    private Admin getAdminData(ResultSet rs) throws SQLException {
        Admin a = new Admin();
        a.setId(rs.getLong("id"));
        a.setAccountNonExpired(rs.getBoolean("account_non_expired"));
        a.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        a.setCreated_at(rs.getDate("created_at"));
        a.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
        a.setEmail(rs.getString("email"));
        a.setEnabled(rs.getBoolean("enabled"));
        a.setPassword(rs.getString("password"));
        a.setUsername(rs.getString("username"));
        return a;
    }
}