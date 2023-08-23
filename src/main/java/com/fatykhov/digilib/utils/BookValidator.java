package com.fatykhov.digilib.utils;


import com.fatykhov.digilib.dao.BookDAO;
import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (book.getBookTitle().equals("")) {
            errors.rejectValue("bookTitle", "", "Название книги не может быть пустым");
        }

        if (book.getBookAuthor().equals("")) {
            errors.rejectValue("bookAuthor", "", "Поле автора книги не может быть пустым");
        }

        if (book.getBookPublicationYear() <= 0 || book.getBookPublicationYear() > LocalDate.now().getYear()) {
            errors.rejectValue("bookPublicationYear", "", "Введен некорректный год публикации");
        }
    }
}
