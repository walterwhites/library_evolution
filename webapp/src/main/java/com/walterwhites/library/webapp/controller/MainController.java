package com.walterwhites.library.webapp.controller;

import com.walterwhites.library.webapp.apiClient.BookClient;
import library.io.github.walterwhites.GetAllBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Configuration
public class MainController {

    @Value("${error.message}")
    private String errorMessage;

    @Value("${application.name}")
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
        model.addAttribute("books", getAllBookResponse);
        return "dashboard";
    }

    @RequestMapping(value = {"/tables"}, method = RequestMethod.GET)
    public String tables(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        return "tables";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        return "login";
    }
}