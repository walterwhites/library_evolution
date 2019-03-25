package com.walterwhites.library.batch_notification.processor;

import com.walterwhites.library.business.utils.EmailService;
import library.io.github.walterwhites.loans.Loans;
import library.io.github.walterwhites.loans.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationItemProcessor implements ItemProcessor<Notification, Notification> {

    private static final Logger log = LoggerFactory.getLogger(NotificationItemProcessor.class);
    static int i = 1;

    @Autowired
    public EmailService emailService;

    @Override
    public Notification process(Notification item) {
        String message = "Hello " + item.getFirstname() + " " + item.getLastname() + ", " +
                item.getReservation().getCreatedDate().getDay() + "/" + item.getReservation().getCreatedDate().getMonth() + "/" +
                item.getReservation().getCreatedDate().getYear() + " you have reserved the book " + item.getReservation().getBookTitle() +
                ", you already has been notified by email the " +
                item.getCreatedDate().getDay() + "/" + item.getCreatedDate().getMonth() + "/" +
                item.getCreatedDate().getYear() +
                " that book is available since 48 hours, but you had not confirmed your reservation on the website" +
               " so you lost your reservation, sorry";

        log.info("Email sended to " + item.getEmail());
        emailService.sendSimpleMessage("hopemagie@gmail.com", "You lost your reservation", message);

        return item;
    }
}