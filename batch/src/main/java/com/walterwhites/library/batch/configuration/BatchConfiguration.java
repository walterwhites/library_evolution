package com.walterwhites.library.batch.configuration;

import com.walterwhites.library.batch.processor.BookItemProcessor;
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
@EnableJpaRepositories("com.walterwhites.library.consumer.repository")
@EntityScan("com.walterwhites.library.model.entity")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Writer writer;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Book> reader() {
        return new FlatFileItemReaderBuilder<Book>()
                .name("BookItemReader")
                .resource(new ClassPathResource("Book.csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"title", "author", "language", "state", "loan_start_date", "loan_end_date"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Book>() {{
                    setTargetType(Book.class);
                }})
                .build();
    }

    @Bean
    public BookItemProcessor processor() {
        return new BookItemProcessor();
    }


    // tag::jobstep[]
    @Bean
    public Job importBookJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Book, Book> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
    // end::jobstep[]
}