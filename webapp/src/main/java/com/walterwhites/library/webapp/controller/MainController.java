package com.walterwhites.library.webapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class MainController {

    @Value("${error.message}")
    private String errorMessage;

    @Value("${application.name}")
    private String appName;

    @Value("${application.author}")
    private String author;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        return "index";
    }

    @RequestMapping(value = {"/index2"}, method = RequestMethod.GET)
    public String index2(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        return "index_2";
    }
}