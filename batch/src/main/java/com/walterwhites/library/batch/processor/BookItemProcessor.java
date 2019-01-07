package com.walterwhites.library.batch.processor;

import com.walterwhites.library.batch.business.Book;
import com.walterwhites.library.batch.business.Client;
import com.walterwhites.library.batch.business.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class BookItemProcessor implements ItemProcessor<Book, Book> {

    private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);

    @Override
    public Book process(Book item) throws Exception {
        final String title = item.getTitle().toLowerCase();
        final String author = item.getAuthor().toLowerCase();
        final String language = item.getLanguage().toLowerCase();
        final String state = item.getState().toLowerCase();

        final Date loan_start_date = new Date();
        final Date loan_end_date = new Date();
        final Library library = new Library();
        final Client clients = new Client();

        final Book transformedBook = new Book(title, author, language, state, loan_start_date, loan_end_date, library, clients);

        log.info("Converting (" + item + ") into (" + transformedBook + ")");

        return transformedBook;
    }
}