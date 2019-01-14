package com.walterwhites.library.consumer.repository.impl;

import com.walterwhites.library.consumer.repository.contract.BookRepository;
import com.walterwhites.library.webservice.jaxb.java.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Override
    @Transactional
    public void refresh(Book book) {
        em.refresh(book);
    }

    @Override
    public List<Book> findByTitle(String title) {
         List<Book> books = (List<Book>) operations.queryForObject(
                "SELECT * FROM book WHERE title = ?",
                Book.class,
                title);
         return books;
    }

    @Override
    public <S extends Book> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(book -> this.em.persist(book));
        return null;
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
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
    public void delete(Book entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }
}