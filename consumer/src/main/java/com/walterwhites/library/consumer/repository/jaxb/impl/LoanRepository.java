package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.loans.*;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository {
    public List<Loans> findAllNotReturnedBook();
    public List<Notification> updateAllNotification();
    public List<Loans> findAllSoonLoanExpired();
    public List<Reservation> findAllReservationFromClient(String username);
    public Date getExpectedReturnDateOfReservation(Long id_of_book);
    public Reservation findLastReservation(String book_title);
    public Boolean updateArchivedReservation(String username, String book_title);
}
