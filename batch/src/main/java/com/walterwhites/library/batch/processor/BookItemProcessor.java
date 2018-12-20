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
    public Book process(final Book book) throws Exception {
        final String title = book.getTitle().toLowerCase();
        final String author = book.getAuthor().toLowerCase();
        final String language = book.getLanguage().toLowerCase();
        final String state = book.getState().toLowerCase();

        final Date loan_start_date = new Date();
        final Date loan_end_date = new Date();
        final Library library = new Library();
        final Client clients = new Client();

        final Book transformedBook = new Book(title, author, language, state, loan_start_date, loan_end_date, library, clients);

        log.info("Converting (" + book + ") into (" + transformedBook + ")");

        return transformedBook;
    }
}