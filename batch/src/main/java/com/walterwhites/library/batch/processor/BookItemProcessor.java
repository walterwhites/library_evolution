package com.walterwhites.library.batch.processor;

import com.walterwhites.library.business.utils.DateUtils;
import com.walterwhites.library.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class BookItemProcessor implements ItemProcessor<Book, Book> {

    private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);
    static int i = 1;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Book process(Book item) throws Exception {
        final String title = item.getTitle().toLowerCase();
        final String author = item.getAuthor().toLowerCase();
        final String language = item.getLanguages().toLowerCase();
        final Integer number = item.getNumber();
        final Client client = new Client();

        client.setFirstname("Flo");
        client.setPassword(passwordEncoder().encode("password"));
        client.setLanguage("fr");
        client.setLastname("flo");

        List<Book> bookList = new LinkedList<Book>();
        final Book transformedBook = new Book(title, author, language, number);
        transformedBook.setMax_number(number + 1);
        if (transformedBook.getNumber() == 0) {
            // create a reservation on book
            transformedBook.setMax_number(number + 2);
            List<Reservation> reservations = new LinkedList<Reservation>();
            Reservation reservation = new Reservation();
            reservation.setCreated_date(new Date());
            reservation.setBook(transformedBook);
            reservation.setClient(client);

            // create a notification on book
            List<Notification> notifications = new LinkedList<Notification>();
            Notification notification = new Notification();
            notification.setCreated_date(new Date());
            notification.setEmail("hopemagie@gmail.com");
            notification.setReservation(reservation);
            notifications.add(notification);

            reservation.setNotifications(notifications);
            reservations.add(reservation);
            transformedBook.setReservations(reservations);
        }
        client.setEmail("client" + i + "@gmail.com");
        client.setUsername("flo" + i);
        i++;

        // library
        List<Library> libraries = new LinkedList<Library>();
        Library library = new Library();
        libraries.add(library);
        library.setName("François Mitterrand");
        library.setPhoneNumber("0666666666");
        library.setName("library of Liverpool");
        library.setAddress("10 Mathew St, Liverpool");
        library.setBooks(bookList);
        transformedBook.setLibraries(libraries);

        bookList.add(transformedBook);

        // loan
        List<Loan> loans = new LinkedList<Loan>();
        Loan loan = new Loan();
        final Date loan_start_date = new Date();
        GregorianCalendar loan_end_date = DateUtils.toXmlGregorianCalendar(new Date()).toGregorianCalendar();
        loan_end_date.add(Calendar.DATE, 28);
        final Date loan_updated_date = new Date();
        loan.setStart_date(loan_start_date);
        loan.setEnd_date(loan_end_date.getTime());
        loan.setUpdated_date(loan_updated_date);
        loan.setRenewed(false);
        loan.setClient(client);
        loan.setBook(transformedBook);
        loans.add(loan);
        transformedBook.setLoans(loans);
        loan.setState("borrowed");
        client.setLoans(loans);

        log.info("Converting (" + item + ") into (" + transformedBook + ")");

        return transformedBook;
    }
}