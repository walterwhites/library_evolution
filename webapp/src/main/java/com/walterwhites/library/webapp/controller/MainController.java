package com.walterwhites.library.webapp.controller;

import com.walterwhites.library.business.parser.BookParser;
import com.walterwhites.library.webapp.apiClient.BookClient;
import library.io.github.walterwhites.Book;
import library.io.github.walterwhites.GetAllBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Value("${error.message}")
    private String errorMessage;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${application.author}")
    private String author;

    @Autowired
    private BookClient bookClient;

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public String dashboard(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        GetAllBookResponse getAllBookResponse = bookClient.getAllBooks();
        List<Book> frenchBooks = BookParser.getFrenchBooks(getAllBookResponse.getBook());
        List<Book> englishBooks = BookParser.getEnglishBooks(getAllBookResponse.getBook());
        model.addAttribute("books", getAllBookResponse);
        model.addAttribute("nbFrenchBooks", frenchBooks.size());
        model.addAttribute("nbEnglishBooks", englishBooks.size());
        return "dashboard";
    }

    @RequestMapping(value = {"/tables"}, method = RequestMethod.GET)
    public String tables(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        GetAllBookResponse getAllBookResponse = bookClient.getAllBooks();
        model.addAttribute("books", getAllBookResponse);
        return "tables";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("appName", appName);
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/profile/notes");
        }
        return new ModelAndView("login");
    }
}