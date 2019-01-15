package com.walterwhites.library.consumer.repository.contract;

import com.walterwhites.library.consumer.jaxb.java.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryWebservice {
    List<Book> findByTitle(String title);
    Book findById(long id);
}