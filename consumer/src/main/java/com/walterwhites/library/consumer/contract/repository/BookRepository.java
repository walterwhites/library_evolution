package com.walterwhites.library.consumer.contract.repository;

import com.walterwhites.library.model.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}