package com.fatykhov.digilib.controllers;

import com.fatykhov.digilib.dao.BookDAO;
import com.fatykhov.digilib.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{bookId}")
    public String show(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}/edit")
    public String edit(Model model, @PathVariable("bookId") int bookId) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/edit";
    }

    @PatchMapping("/{bookId}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("bookId") int bookId) {
        bookDAO.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{bookId}")
    public String delete(@PathVariable("bookId") int bookId) {
        bookDAO.delete(bookId);
        return "redirect:/books";
    }
}
