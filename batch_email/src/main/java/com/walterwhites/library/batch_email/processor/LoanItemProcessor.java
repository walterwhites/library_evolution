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
        /*File csv = new File(LoanItemProcessor.class.getClassLoader().getResource("user_email.csv").getFile());
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String str = item.getClient().getEmail() + "," + item.getClient().getFirstname() + " " +
                item.getClient().getLastname() + "," + item.getBook().getTitle();
        String[] record = str.split(",");
        writer.writeNext(record);
        writer.close();*/
        log.info("Email sended to " + item.getClient().getEmail());
        emailService.sendSimpleMessage("hopemagie@gmail.com", "Book loan", "Hello " +
                        item.getClient().getFirstname() + " " + item.getClient().getLastname() +
                ", you have borrowed the " + item.getStartDate().getDay() + "/" + item.getStartDate().getMonth() + "/" +
                item.getStartDate().getYear() + " the book " + item.getBook().getTitle() + ", you had to return it " +
                "before the " + item.getEndDate().getDay() + "/" + item.getEndDate().getMonth() + "/" + item.getEndDate().getYear() +
                " please can return it");

        return item;
    }
}