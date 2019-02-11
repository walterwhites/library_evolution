package com.walterwhites.library.business.parser;

import com.walterwhites.library.business.client.SentryJClient;
import library.io.github.walterwhites.Book;

import java.util.LinkedList;
import java.util.List;

final public class BookParser {

    static public List<Book> getFrenchBooks(List<Book> books) {

        List<Book> frenchBooks = new LinkedList<Book>();

        for (Book book : books) {
            SentryJClient sentryJClient = SentryJClient.init();
            sentryJClient.addTag("author", book.getAuthor());
            sentryJClient.sendSimpleEvent("message");
            if (book.getLanguages().value().equals("fr")) {
                frenchBooks.add(book);
            }
        }
        return frenchBooks;
    }

    static public List<String> getBookNames(List<Book> books) {

        List<String> bookNames = new LinkedList<String>();

        for (Book book : books) {
            bookNames.add(book.getTitle());
        }
        return bookNames;
    }

    static public List<Book> getEnglishBooks(List<Book> books) {

        List<Book> englishBooks = new LinkedList<Book>();

        for (Book book : books) {
            SentryJClient sentryJClient = SentryJClient.init();
            sentryJClient.addExtra("author", book.getAuthor());
            sentryJClient.sendSimpleEvent("message");
            if (book.getLanguages().value().equals("en")) {
                englishBooks.add(book);
            }
        }
        return englishBooks;
    }
}
