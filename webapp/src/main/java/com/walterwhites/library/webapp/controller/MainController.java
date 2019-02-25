package com.walterwhites.library.webapp.controller;

import com.walterwhites.library.business.parser.BookParser;
import com.walterwhites.library.business.utils.DateUtils;
import com.walterwhites.library.model.pojo.MyUser;
import com.walterwhites.library.webapp.apiClient.BookClient;
import library.io.github.walterwhites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @RequestMapping(value = "/reserved-book", method = {RequestMethod.POST})
    public String reservedBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        if (hasUserRole) {
            UserDetails client = (UserDetails) auth.getPrincipal();
            PostBookBorrowedResponse postBookBorrowedResponse = bookClient.postBookBorrowed(book.getId(),  ((MyUser) client).getId());
            Long loan_id = postBookBorrowedResponse.getId();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(Calendar.DATE, 28);
            String calendar = DateUtils.formatDayMonthYear(gregorianCalendar);
            redirectAttributes.addFlashAttribute("message", "You have renewed " + book.getTitle()
                    + " until to " + calendar);
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        }
        return "redirect:/tables";
    }

    @RequestMapping(value = "/renewed-book", method = {RequestMethod.POST})
    public String renewedBook(@ModelAttribute("loan") Loans loans, RedirectAttributes redirectAttributes, @RequestParam("title") String title) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        if (hasUserRole) {
            PostBookRenewedResponse postBookRenewedResponse = bookClient.postBookRenewed(loans.getId());
            redirectAttributes.addFlashAttribute("message", "You have renewed " + title + " for 4 weeks");
            redirectAttributes.addFlashAttribute("alertClass", "alert-warning");
        }
        return "redirect:/loans";
    }

    @RequestMapping(value = "/return-book", method = {RequestMethod.POST})
    public String returnBook(@ModelAttribute("loan") Loans loans, RedirectAttributes redirectAttributes, @RequestParam("title") String title) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        if (hasUserRole) {
            PostBookReturnedResponse postBookReturnedResponse = bookClient.postBookReturned(loans.getId());
            redirectAttributes.addFlashAttribute("message", "You have returned " + title);
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        }
        return "redirect:/loans";
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        if (hasUserRole) {
            User client = (User) auth.getPrincipal();
            String username = client.getUsername();
            GetAllBookFromClientResponse getAllBookResponseFromClient = bookClient.getAllBorrowedBooksFromClient(username);
            List<String> bookNames = BookParser.getBookNamesAvailable(getAllBookResponseFromClient.getBook());
            model.addAttribute("bookNames", bookNames);
            model.addAttribute("connected", true);
        }
        model.addAttribute("appName", appName);
        model.addAttribute("author", author);
        GetAllBookResponse getAllBookResponse = bookClient.getAllBooks();
        model.addAttribute("books", getAllBookResponse);
        return "tables";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, String error, String logout) {
        model.addAttribute("appName", appName);
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMINISTRATOR"));
        model.addAttribute("appName", appName);
        if (!hasAdminRole) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("auth/admin");
    }

    @RequestMapping(value = "/loans", method = RequestMethod.GET)
    public ModelAndView loans(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        model.addAttribute("appName", appName);
        if (!hasUserRole) {
            return new ModelAndView("redirect:/login");
        }
        this.getAllBooksFromClient(auth, model);
        return new ModelAndView("auth/loans");
    }

    private void getAllBooksFromClient(Authentication auth, Model model) {
        if (auth != null) {
            User client = (User) auth.getPrincipal();
            String username = client.getUsername();
            GetAllBookFromClientResponse getAllBookResponseFromClient = bookClient.getAllBooksFromClient(username);
            model.addAttribute("books_client", getAllBookResponseFromClient);
        }
    }
}