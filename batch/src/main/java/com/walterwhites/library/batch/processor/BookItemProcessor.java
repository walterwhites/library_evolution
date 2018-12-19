package com.walterwhites.library.batch.processor;

import com.walterwhites.library.batch.business.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class BookItemProcessor implements ItemProcessor<Book, Book> {

    private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);

    @Override
    public Book process(final Book book) throws Exception {
        final String title = book.getTitle().toLowerCase();
        final String author = book.getAuthor().toLowerCase();
        final String language = book.getLanguage().toLowerCase();
        final String state = book.getState().toLowerCase();

        final Book transformedBook = new Book(title, author, language, state, book.getLoan_start_date(), book.getLoan_end_date(), book.getLibrary(), book.getClient());

        log.info("Converting (" + book + ") into (" + transformedBook + ")");

        return transformedBook;
    }
}