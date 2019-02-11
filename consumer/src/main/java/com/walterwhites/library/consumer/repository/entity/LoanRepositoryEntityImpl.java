package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Book;
import com.walterwhites.library.model.entity.Client;
import com.walterwhites.library.model.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    private ClientRepositoryImpl clientRepositoryImpl;


    @Override
    @Transactional
    public void refresh(Loan loan) {
        em.refresh(loan);
    }

    public Long saveBookBorrowed(library.io.github.walterwhites.Book book, Long client_id) {
        Loan entityLoan = addLoan(book, client_id);
        this.em.persist(entityLoan);
        Long loan_id = this.findById(entityLoan.getId()).get().getId();
        return loan_id;
    }

    private Loan addLoan(library.io.github.walterwhites.Book book, long client_id) {

        Loan entityLoan = new Loan();

        List<Book> bookList = new LinkedList<>();
        Book entityBook = bookRepositoryEntity.findBookById(book.getId());
        bookList.add(entityBook);

        entityLoan.setUpdated_date(book.getLoans().getUpdatedDate().toGregorianCalendar().getTime());
        entityLoan.setEnd_date(book.getLoans().getEndDate().toGregorianCalendar().getTime());
        entityLoan.setStart_date(book.getLoans().getStartDate().toGregorianCalendar().getTime());
        entityLoan.setRenewed(book.getLoans().isRenewed());
        entityLoan.setState("borrowed");

        Client client = clientRepositoryImpl.findById(client_id).get();

        entityLoan.setClient(client);
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
}
