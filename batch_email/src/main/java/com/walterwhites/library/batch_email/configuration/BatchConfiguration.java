package com.walterwhites.library.batch_email.configuration;

import com.walterwhites.library.batch_email.processor.LoanItemProcessor;
import com.walterwhites.library.business.utils.DateUtils;
import com.walterwhites.library.consumer.repository.entity.LoanRepositoryEntity;
import com.walterwhites.library.model.entity.Loan;
import library.io.github.walterwhites.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@ComponentScan
@EnableJpaRepositories("com.walterwhites.library.consumer.repository.entity")
@EntityScan("com.walterwhites.library.model.entity")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public LoanRepositoryEntity loanRepositoryEntity;

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
    public ItemReader<Map<Loan, Long>> jdbcReader(DataSource dataSource) {

        GetAllBookFromClientResponse getAllBookResponseFromClient = bookClient.getAllBooksFromClient(username);


        return new JdbcCursorItemReaderBuilder<Map<Loan, Long>>()
                .dataSource(dataSource)
                .name("jdbc-reader")
                .sql("select loan.id, client.email, client.firstname, client.lastname, book.title from loan LEFT JOIN client_loans ON loan.id = client_loans.loans_id LEFT JOIN client ON client_loans.client_id = client.id LEFT JOIN book ON loan.book_id = book.id")
                .rowMapper((rs, i) -> {
                    return getLoanData(rs);
                })
                .build();
    }


    @Bean
    public Step stepLoan() {

        RepositoryItemReader<Loan> loanReader = new RepositoryItemReader<>();
        loanReader.setRepository(loanRepositoryEntity);
        loanReader.setMethodName("findAllNotReturnedBook");

        return stepBuilderFactory.get("stepLoan")
                .<Loan, Loan> chunk(10)
                .reader(loanReader)
                .processor(loanProcessor())
                .writer(new ItemWriter<Loan>() {
                    @Override
                    public void write(List<? extends Loan> items) throws Exception {

                    }
                })
                .build();
    }
}