package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@Configuration
@EnableAutoConfiguration
@Transactional
public class ReservationRepositoryEntityImpl implements ReservationRepositoryEntity {

    @PersistenceContext()
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;


    @Override
    @Transactional
    public void refresh(Reservation reservation) {
        em.refresh(reservation);
    }

    public Long cancelReservation(Reservation entityReservation) {
        entityReservation.setState("cancelled");
        this.em.merge(entityReservation);
        return entityReservation.getId();
    }

    @Override
    public <S extends Reservation> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Reservation> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long aLong) {
        Reservation reservation = this.em.find(Reservation.class, aLong);
        return Optional.ofNullable(reservation);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Reservation> findAll() {
        return null;
    }

    @Override
    public Iterable<Reservation> findAllById(Iterable<Long> longs) {
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
    public void delete(Reservation entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Reservation> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<Reservation> findAll(Sort sort) {
        return em.createQuery(
                "SELECT * FROM reservation", Reservation.class
        ).getResultList();
    }

    public Iterable<Reservation> findAllNotReturnedBook(Sort sort) {
        return em.createQuery(
                "SELECT * FROM reservation", Reservation.class
        ).getResultList();
    }

    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        return null;
    }
}
