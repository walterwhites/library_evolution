package com.walterwhites.library.consumer.repository.jaxb.impl;

import com.walterwhites.library.business.debug.MyLogger;
import library.io.github.walterwhites.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public interface BookRepository {
    static Logger myLogger = MyLogger.init();
    List<Book> findByTitle(String title);
    Book findById(Long id);
    List<Book> findAllBooks();
    List<Book> findAllBorrowedBooksFromClient(String id);
    List<Book> findAllBooksFromClient(String username);
}
