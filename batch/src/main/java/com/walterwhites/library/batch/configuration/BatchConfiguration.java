package com.walterwhites.library.batch.configuration;

import com.walterwhites.library.batch.business.Book;
import com.walterwhites.library.batch.processor.BookItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // tag::readerwriterprocessor[]
    @Bean
    FlatFileItemReader<Book> bookFlatFileItemReader() {
        FlatFileItemReader<Book> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("Book.csv"));
        flatFileItemReader.setLinesToSkip(1);
        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"title", "author", "language", "state", "loan_start_date", "loan_end_date", "library", "client"});

        BeanWrapperPersonalized<Book> fieldSetMapper = new BeanWrapperPersonalized<>();
        fieldSetMapper.setTargetType(Book.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;

    }

    @Bean
    public BookItemProcessor processor() {
        return new BookItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Book> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Book>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO book (title, author, language, state, loan_start_date, loan_end_date, library, client) VALUES (" +
                        ":title, :author, :language, :state, :loan_start_date, :loan_end_date, :library, client)")
                .dataSource(dataSource)
                .build();
    }
    // end::readerwriterprocessor[]

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
    public Step step1(JdbcBatchItemWriter<Book> writer) {
        return stepBuilderFactory.get("step1")
                .<Book, Book> chunk(10)
                .reader(bookFlatFileItemReader())
                .processor(processor())
                .writer(writer)
                .build();
    }
    // end::jobstep[]
}