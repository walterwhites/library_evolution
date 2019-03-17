package com.walterwhites.library.consumer.repository.jaxb.impl;

import com.walterwhites.library.business.utils.DateUtils;
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
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
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

    private static List<Notification> notifications = new LinkedList<>();
    private static List<Reservation> reservations = new LinkedList<>();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcOperations operations;

    @Override
    public List<Loans> findAllNotReturnedBook() {
        loans = (List<Loans>) operations.query(
                "SELECT loan.id, loan.end_date, loan.start_date, client.email, client.firstname, client.lastname, book.title " +
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

    @Override
    public List<Reservation> findAllReservationFromClient(String username) {
        reservations = (List<Reservation>) operations.query(
                "SELECT\n" +
                        "  reservation.* AS reservation,\n" +
                        "  client.firstname AS firstname,\n" +
                        "  client.lastname AS lastname,\n" +
                        "  client.username AS username,\n" +
                        "  book.max_number AS max_number,\n" +
                        "  book.title AS book_title\n" +
                        "FROM\n" +
                        "  reservation\n" +
                        "    LEFT JOIN client ON reservation.client_id = client.id\n" +
                        "    LEFT JOIN book ON reservation.book_id = book.id\n" +
                        "WHERE username = ?",
                (rs, rownumber) -> {
                    return getReservationData(rs);
                }, username);
        return reservations;
    }

    @Override
    public List<Notification> updateAllNotification() {
        String sql = "SELECT client.firstname, client.lastname, reservation.client_id, notification.id as notification_id, notification.state as notification_state, notification.email, " +
                "notification.created_date as notification_created_date, notification.reservation_id, " +
                "reservation.created_date as reservation_created_date, reservation.state as reservation_state, " +
                "reservation.book_id, book.title FROM notification LEFT JOIN reservation ON notification.id = " +
                "reservation.notification_id LEFT JOIN book ON reservation.book_id = book.id " +
                "LEFT JOIN client ON reservation.client_id = client.id WHERE notification.state = " +
                "'pending' AND reservation.state = 'pending' AND notification.created_date + INTERVAL '2' DAY <= NOW() \n";
        notifications = (List<Notification>) operations.query(sql,
                (rs, rownumber) -> {
                    Notification result = getNotificationData(rs);
                    operations.batchUpdate(
                            "UPDATE notification SET state = 'archived' WHERE notification.id = " +
                                    result.getId());
                    operations.batchUpdate(
                            "UPDATE reservation SET state = 'archived' WHERE reservation.notification_id = " +
                                    result.getId());
                    result.setState("archived");
                    return result;
                });
        return notifications;

    }

    public List<Loans> findAllSoonLoanExpired() {
        loans = (List<Loans>) operations.query(
                "SELECT loan.id, loan.end_date, loan.start_date, client.email, client.firstname, client.lastname, book.title " +
                        "FROM loan LEFT JOIN client_loans ON loan.id = client_loans.loans_id " +
                        "LEFT JOIN client ON client_loans.client_id = client.id " +
                        "LEFT JOIN book " +
                        "ON loan.book_id = book.id " +
                        "WHERE loan.state = 'borrowed' AND " +
                        "DATE(loan.end_date) <= DATE(NOW()) + INTERVAL '5' DAY AND " +
                        "client.alert_email = TRUE",

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
        XMLGregorianCalendar end_date = DateUtils.toXmlGregorianCalendar(rs.getDate("end_date"));
        loan.setEndDate(end_date);
        XMLGregorianCalendar start_date = DateUtils.toXmlGregorianCalendar(rs.getDate("start_date"));
        loan.setStartDate(start_date);
        book.setTitle(rs.getString("title"));
        client.setFirstname(rs.getString("firstname"));
        client.setLastname(rs.getString("lastname"));
        client.setEmail(rs.getString("email"));

        loan.setBook(book);
        loan.setClient(client);
        return loan;
    }

    private Notification getNotificationData(ResultSet rs) throws SQLException {

        Notification notification = new Notification();
        Reservation reservation = new Reservation();

        notification.setId(rs.getLong("notification_id"));
        XMLGregorianCalendar notification_created_date = DateUtils.toXmlGregorianCalendar(rs.getDate("notification_created_date"));
        notification.setCreatedDate(notification_created_date);
        notification.setEmail(rs.getString("email"));
        notification.setState(rs.getString("notification_state"));
        notification.setFirstname(rs.getString("firstname"));
        notification.setLastname(rs.getString("lastname"));

        reservation.setBookTitle(rs.getString("title"));
        XMLGregorianCalendar reservation_created_date = DateUtils.toXmlGregorianCalendar(rs.getDate("reservation_created_date"));
        reservation.setCreatedDate(reservation_created_date);
        reservation.setId(rs.getLong("reservation_id"));
        reservation.setClientId(rs.getLong("client_id"));
        notification.setReservation(reservation);

        return notification;
    }

    private Reservation getReservationData(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setClientId(rs.getLong("client_id"));
        r.setId(rs.getLong("id"));
        r.setState(rs.getString("state"));
        r.setMaxNumber(new BigInteger(Integer.valueOf(rs.getInt("max_number")).toString()));
        XMLGregorianCalendar created_date = DateUtils.toXmlGregorianCalendar(rs.getDate("created_date"));
        r.setCreatedDate(created_date);
        r.setBookTitle(rs.getString("book_title"));

        return r;
    }
}