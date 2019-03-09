package com.walterwhites.library.batch_remind_expiration.processor;

import com.walterwhites.library.business.utils.EmailService;
import library.io.github.walterwhites.loans.Loans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanItemProcessor implements ItemProcessor<Loans, Loans> {

    private static final Logger log = LoggerFactory.getLogger(LoanItemProcessor.class);
    static int i = 1;

    @Autowired
    public EmailService emailService;

    @Override
    public Loans process(Loans item) {
        String message = "Hello " +
                item.getClient().getFirstname() + " " + item.getClient().getLastname() + " " +
                item.getStartDate().getDay() + "/" + item.getStartDate().getMonth() + "/" +
                item.getStartDate().getYear() + " you have borrowed the book " + item.getBook().getTitle() + ", you had to return it " +
                "before the " + item.getEndDate().getDay() + "/" + item.getEndDate().getMonth() + "/" + item.getEndDate().getYear() +
                " please can return it or renewed it";
        String message_admin = item.getClient().getFirstname() + " " + item.getClient().getLastname() +
                " have borrowed the book " + item.getBook().getTitle() + " from " +
                item.getStartDate().getDay() + "/" + item.getStartDate().getMonth() + "/" +
                item.getStartDate().getYear() + " to " +
                item.getEndDate().getDay() + "/" + item.getEndDate().getMonth() + "/" + item.getEndDate().getYear();

        log.info("Email sended to " + item.getClient().getEmail());
        emailService.sendSimpleMessage("hopemagie@gmail.com", "Client has a soon expired loan", message_admin);
        emailService.sendSimpleMessage("hopemagie@gmail.com", "You have a soon expired loan", message);

        return item;
    }
}