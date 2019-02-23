package com.walterwhites.library.batch_email.configuration;

import com.walterwhites.library.batch_email.apiClient.LoanClient;
import com.walterwhites.library.batch_email.processor.LoanItemProcessor;
import com.walterwhites.library.consumer.repository.entity.LoanRepositoryEntity;
import library.io.github.walterwhites.loans.GetAllNotReturnedBookResponse;
import library.io.github.walterwhites.loans.Loans;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@EnableBatchProcessing
@ComponentScan
@EntityScan("com.walterwhites.library.model.entity")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public LoanRepositoryEntity loanRepositoryEntity;

    @Autowired
    public LoanClient loanClient;

    @Bean
    public LoanItemProcessor loanProcessor() {
        return new LoanItemProcessor();
    }

    @Bean
    public Job importLoanJob(JobCompletionNotificationListener listener, Step stepLoan) {
        return jobBuilderFactory.get("importLoanJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepLoan)
                .end()
                .build();
    }

    @Bean
    public ItemReader<Loans> loanReader(){
        GetAllNotReturnedBookResponse getAllNotReturnedBookResponse = loanClient.getAllNotReturnedBook();
        ItemReader<Loans> itemReader = new ListItemReader<Loans>(getAllNotReturnedBookResponse.getBooksNotReturned());
        return itemReader;
    }


    @Bean
    public Step stepLoan() {
        return stepBuilderFactory.get("stepLoan")
                .<Loans, Loans> chunk(10)
                .reader(loanReader())
                .processor(loanProcessor())
                .writer(new ItemWriter<Loans>() {
                    @Override
                    public void write(List<? extends Loans> items) throws Exception {

                    }
                })
                .build();
    }
}