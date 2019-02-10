package com.walterwhites.library.batch.configuration;

import com.walterwhites.library.batch.processor.AdminItemProcessor;
import com.walterwhites.library.batch.processor.BookItemProcessor;
import com.walterwhites.library.model.entity.Admin;
import com.walterwhites.library.model.entity.Book;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
    private Writer writer;

    @Autowired
    private AdminWriter adminWriter;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Book> bookReader() {
        return new FlatFileItemReaderBuilder<Book>()
                .name("BookItemReader")
                .resource(new ClassPathResource("Book.csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"title", "author", "language", "state", "obtaining_date", "number"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Book>() {{
                    setTargetType(Book.class);
                }})
                .build();
    }

    @Bean
    public BookItemProcessor bookProcessor() {
        return new BookItemProcessor();
    }

    @Bean
    public AdminItemProcessor adminProcessor() {
        return new AdminItemProcessor();
    }

    @Bean
    public Job importBookJob(JobCompletionNotificationListener listener, Step stepBook) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepBook)
                .end()
                .build();
    }

    @Bean
    public Step stepBook() {
        return stepBuilderFactory.get("stepBook")
                .<Book, Book> chunk(10)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<Admin> adminReader() {
        return new FlatFileItemReaderBuilder<Admin>()
                .name("AdminItemReader")
                .resource(new ClassPathResource("Admin.csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"firstname", "email"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Admin>() {{
                    setTargetType(Admin.class);
                }})
                .build();
    }

    @Bean
    public Job importAdminJob(JobCompletionNotificationListener listener, Step stepAdmin) {
        return jobBuilderFactory.get("importAdminJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepAdmin)
                .end()
                .build();
    }

    @Bean
    public Step stepAdmin() {
        return stepBuilderFactory.get("stepAdmin")
                .<Admin, Admin> chunk(10)
                .reader(adminReader())
                .processor(adminProcessor())
                .writer(adminWriter)
                .build();
    }
}