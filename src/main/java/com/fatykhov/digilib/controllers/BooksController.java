package com.fatykhov.digilib.controllers;

//import com.fatykhov.digilib.dao.BookDAO;
//import com.fatykhov.digilib.dao.PersonDAO;
import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import com.fatykhov.digilib.services.BooksService;
import com.fatykhov.digilib.services.PeopleService;
//import com.fatykhov.digilib.utils.BookValidator;
//import jdk.tools.jlink.internal.JmodArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

//    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
//        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer pageNum,
                        @RequestParam(value = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam(value = "sort_by_year", required = false) String sort_by_year) {
        if (pageNum == null || books_per_page == null)
            model.addAttribute("books", booksService.findAll(sort_by_year));
        else
            model.addAttribute("books", booksService.findAll(pageNum, books_per_page, sort_by_year));
        return "books/index";
    }

    @GetMapping("/{bookId}")
    public String show(@PathVariable("bookId") int bookId, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(bookId));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBook() {
        return "books/search";
    }

    @PostMapping("/search")
    public String foundBook(Model model, @RequestParam("query") String query) {
        model.addAttribute("books", booksService.searchByTitle(query));
        return "books/search";
    }

    @GetMapping("/{bookId}/edit")
    public String edit(Model model, @PathVariable("bookId") int bookId) {
        model.addAttribute("book", booksService.findOne(bookId));
        return "books/edit";
    }

    @PatchMapping("/{bookId}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("bookId") int bookId) {
//        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{bookId}")
    public String delete(@PathVariable("bookId") int bookId) {
        booksService.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{bookId}/free")
    public String freeBook(@PathVariable("bookId") int bookId) {
        booksService.freeBook(bookId);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{bookId}/assign")
    public String assignBook(@ModelAttribute("person") Person person, @PathVariable("bookId") int bookId) {
        booksService.assignBook(person, bookId);
        return "redirect:/books/" + bookId;
    }
}
