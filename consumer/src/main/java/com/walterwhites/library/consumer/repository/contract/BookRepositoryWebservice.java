package com.walterwhites.library.consumer.repository.contract;

import org.springframework.stereotype.Repository;

import library.io.github.walterwhites.Book;
import java.util.List;

@Repository
public interface BookRepositoryWebservice {
    List<Book> findByTitle(String title);
    Book findById(long id);
}