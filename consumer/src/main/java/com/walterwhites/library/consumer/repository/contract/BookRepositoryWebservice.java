package com.walterwhites.library.consumer.repository.contract;

import com.walterwhites.library.business.debug.MyLogger;
import org.springframework.stereotype.Repository;

import library.io.github.walterwhites.Book;
import java.util.List;
import java.util.logging.Logger;

@Repository
public interface BookRepositoryWebservice {
    static Logger myLogger = MyLogger.init();
    List<Book> findByTitle(String title);
    Book findById(long id);
}