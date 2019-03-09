package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.business.utils.DateUtils;
import com.walterwhites.library.model.entity.Book;
import com.walterwhites.library.model.entity.Client;
import com.walterwhites.library.model.entity.Loan;
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
public class LoanRepositoryEntityImpl implements LoanRepositoryEntity {

    @PersistenceContext()
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Autowired
    private BookRepositoryEntityImpl bookRepositoryEntity;

    @Autowired
    private ClientRepositoryEntityImpl clientRepositoryImpl;


    @Override
    @Transactional
    public void refresh(Loan loan) {
        em.refresh(loan);
    }

    public Long bookHasBeenProlonged(Loan entityLoan) {
        GregorianCalendar end_date = DateUtils.toXmlGregorianCalendar(entityLoan.getEnd_date()).toGregorianCalendar();
        end_date.add(Calendar.DATE, 28);
        entityLoan.setUpdated_date(new Date());
        entityLoan.setEnd_date(end_date.getTime());
        entityLoan.setRenewed(true);
        this.em.merge(entityLoan);
        return entityLoan.getId();
    }

    public Long saveBookBorrowed(library.io.github.walterwhites.Book book, Long client_id) {
        Loan entityLoan = addLoan(book, client_id);
        this.em.persist(entityLoan);
        Long loan_id = this.findById(entityLoan.getId()).get().getId();
        return loan_id;
    }

    public Long bookHasBeenReturned(Loan entityLoan) {
        entityLoan.setState("returned");
        entityLoan.setUpdated_date(new Date());
        entityLoan.setEnd_date(new Date());
        entityLoan.getBook().setNumber(entityLoan.getBook().getNumber() + 1);
        this.em.merge(entityLoan);
        return entityLoan.getId();
    }

    private Loan addLoan(library.io.github.walterwhites.Book book, long client_id) {
        Loan entityLoan = new Loan();
        List<Book> bookList = new LinkedList<>();
        Book entityBook = bookRepositoryEntity.findBookById(book.getId());
        bookList.add(entityBook);

        //GregorianCalendar end_date = book.getLoans().getEndDate().toGregorianCalendar();
        GregorianCalendar end_date = DateUtils.toXmlGregorianCalendar(new Date()).toGregorianCalendar();
        GregorianCalendar start_date = DateUtils.toXmlGregorianCalendar(new Date()).toGregorianCalendar();
        GregorianCalendar updated_date = DateUtils.toXmlGregorianCalendar(new Date()).toGregorianCalendar();

        end_date.add(Calendar.DATE, 28);
        entityLoan.setUpdated_date(updated_date.getTime());
        entityLoan.setEnd_date(end_date.getTime());
        entityLoan.setStart_date(start_date.getTime());
        entityLoan.setRenewed(false);
        entityLoan.setState("borrowed");
        entityBook.setNumber(entityBook.getNumber() - 1);

        Client client = clientRepositoryImpl.findById(client_id).get();

        entityLoan.setClient(client);
        entityLoan.setBook(entityBook);
        client.getLoans().add(entityLoan);
        entityBook.getLoans().add(entityLoan);

        return entityLoan;
    }

    @Override
    public <S extends Loan> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Loan> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Loan> findById(Long aLong) {
        Loan loans = this.em.find(Loan.class, aLong);
        return Optional.ofNullable(loans);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Loan> findAll() {
        return null;
    }

    @Override
    public Iterable<Loan> findAllById(Iterable<Long> longs) {
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
    public void delete(Loan entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Loan> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<Loan> findAll(Sort sort) {
        return em.createQuery(
                "SELECT * FROM loan", Loan.class
        ).getResultList();
    }

    public Iterable<Loan> findAllNotReturnedBook(Sort sort) {
        return em.createQuery(
                "SELECT * FROM loan", Loan.class
        ).getResultList();
    }

    @Override
    public Page<Loan> findAll(Pageable pageable) {
        return null;
    }
}
