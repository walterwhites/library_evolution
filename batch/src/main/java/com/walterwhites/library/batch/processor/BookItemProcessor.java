package com.walterwhites.library.batch.processor;

import com.walterwhites.library.model.entity.Book;
import com.walterwhites.library.model.entity.Client;
import com.walterwhites.library.model.entity.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;
import java.util.HashSet;

public class BookItemProcessor implements ItemProcessor<Book, Book> {

    private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);

    @Override
    public Book process(Book item) throws Exception {
        final String title = item.getTitle().toLowerCase();
        final String author = item.getAuthor().toLowerCase();
        final String language = item.getLanguages().toLowerCase();
        final String state = item.getState().toLowerCase();

        final Date loan_start_date = new Date();
        final Date loan_end_date = new Date();
        final Library library = new Library();
        final Client client = new Client();
        library.setName("François Mitterrand");
        client.setFirstname("Flo");

        final Book transformedBook = new Book(title, author, language, state, loan_start_date, loan_end_date);
        transformedBook.setLibraries(new HashSet<>());
        transformedBook.setClients(new HashSet<>());
        transformedBook.addClient(client);
        transformedBook.addLibrary(library);

        log.info("Converting (" + item + ") into (" + transformedBook + ")");

        return transformedBook;
    }
}