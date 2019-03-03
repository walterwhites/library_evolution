package com.walterwhites.library.batch_email.configuration;

import com.walterwhites.library.batch_email.apiClient.LoanClient;
import com.walterwhites.library.batch_email.processor.LoanItemProcessor;
import library.io.github.walterwhites.loans.GetAllNotReturnedBookResponse;
import library.io.github.walterwhites.loans.Loans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableBatchProcessing
@Configuration
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
@EnableJpaRepositories("com.walterwhites.library.consumer.repository.entity")
@EntityScan("com.walterwhites.library.model.entity")
@ComponentScan("com.walterwhites.library")
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(LoanItemProcessor.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public LoanClient loanClient;

    @Bean
    @StepScope
    public LoanItemProcessor loanProcessor() {
        return new LoanItemProcessor();
    }

    @Bean
    public Job importLoanJob(JobCompletionNotificationListener listener, Step stepLoan) {
        System.out.println("test234234");
        return jobBuilderFactory.get("importLoanJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepLoan)
                .end()
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Loans> loanReader(){
        GetAllNotReturnedBookResponse getAllNotReturnedBookResponse = loanClient.getAllNotReturnedBook();
        ItemReader<Loans> itemReader = new ListItemReader<Loans>(getAllNotReturnedBookResponse.getLoan());
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