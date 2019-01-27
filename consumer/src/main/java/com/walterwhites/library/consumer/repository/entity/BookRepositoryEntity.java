package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryEntity extends CrudRepository<Book, Long> {
    void refresh(Book book);
    List<Book> findByTitle(String title);
}