package com.fatykhov.digilib.dao;

import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int bookId)  {
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?", new Object[]{bookId},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(personId, bookTitle, bookAuthor, bookPublicationYear) VALUES (?, ?, ?, ?)",
                null, book.getBookTitle(), book.getBookAuthor(), book.getBookPublicationYear());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET bookTitle=?, bookAuthor=?, bookPublicationYear=? WHERE bookId=?",
                updatedBook.getBookTitle(), updatedBook.getBookAuthor(), updatedBook.getBookPublicationYear(), bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", bookId);
    }

    public List<Book> showBookList(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId=?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

//    public Book showPersonWhoTookBook(int bookId) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?", new Object[]{bookId},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }

    public void freeBook(int bookToFreeId) {
        jdbcTemplate.update("UPDATE Book SET personId=null WHERE bookId=?", bookToFreeId);
    }

    public void assignBook(Person personToAssign, int bookToAssignId) {
        jdbcTemplate.update("UPDATE Book SET personId=? WHERE bookId=?", personToAssign.getPersonId(), bookToAssignId);
    }
}
