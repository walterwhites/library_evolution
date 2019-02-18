package com.walterwhites.library.batch_email.processor;

import com.walterwhites.library.model.entity.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LoanItemProcessor implements ItemProcessor<Loan, Loan> {

    private static final Logger log = LoggerFactory.getLogger(LoanItemProcessor.class);
    static int i = 1;

    @Override
    public Loan process(Loan item) throws Exception {

        Loan transformedLoan = new Loan();

        return transformedLoan;
    }
}