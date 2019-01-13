package com.walterwhites.library.batch.configuration;

import com.walterwhites.library.consumer.repository.impl.BookRepositoryImpl;
import com.walterwhites.library.model.entity.Book;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Writer implements ItemWriter<Book> {

    private final BookRepositoryImpl bookRepository;

    @Autowired
    public Writer(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Book> books) throws Exception {
        bookRepository.saveAll(books);
    }

}