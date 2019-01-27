package com.walterwhites.library.business.parser;

import library.io.github.walterwhites.Book;

import java.util.LinkedList;
import java.util.List;

final public class BookParser {

    static public List<Book> getFrenchBooks(List<Book> books) {

        List<Book> frenchBooks = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getLanguages().value().equals("fr")) {
                frenchBooks.add(book);
            }
        }
        return frenchBooks;
    }

    static public List<Book> getEnglishBooks(List<Book> books) {

        List<Book> englishBooks = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getLanguages().value().equals("en")) {
                englishBooks.add(book);
            }
        }
        return englishBooks;
    }
}
