package com.walterwhites.library.batch_email.processor;

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
    public Loans process(Loans item) throws Exception {
        log.info("client's email");
        log.info(Long.toString(item.getId()));
        emailService.sendSimpleMessage("hopemagie@gmail.com", "Book loan", "Hello " +
                        item.getClient().getFirstname() + " " + item.getClient().getLastname() +
                ", you have borrowed the " + item.getStartDate().getDay()/item.getStartDate().getMonth()/
                item.getStartDate().getYear() + " the book" + item.getBook().getTitle() + ", you had to return it " +
                "before the " + item.getEndDate().getDay()/item.getEndDate().getMonth()/item.getEndDate().getYear() +
                " please can return it");

        return item;
    }
}