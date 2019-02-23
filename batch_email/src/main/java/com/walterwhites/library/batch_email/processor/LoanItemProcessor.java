package com.walterwhites.library.batch_email.processor;

import library.io.github.walterwhites.loans.Loans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LoanItemProcessor implements ItemProcessor<Loans, Loans> {

    private static final Logger log = LoggerFactory.getLogger(LoanItemProcessor.class);
    static int i = 1;

    @Override
    public Loans process(Loans item) throws Exception {
        log.info("client's email");
        log.info(item.getEndDate().toString());
        return item;
    }
}