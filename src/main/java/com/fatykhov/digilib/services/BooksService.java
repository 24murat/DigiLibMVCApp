package com.fatykhov.digilib.services;

import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import com.fatykhov.digilib.repositories.BooksRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll(String sortByYear) {
        if (Boolean.parseBoolean(sortByYear))
            return booksRepository.findAll(Sort.by("bookPublicationYear"));
        else if (sortByYear == null)
            return booksRepository.findAll();
        else
            return booksRepository.findAll();
    }

    public List<Book> findAll(int pageNum, int itemsPerPage, String sortByYear) {
        if (Boolean.parseBoolean(sortByYear))
            return booksRepository.findAll
                    (PageRequest.of(pageNum, itemsPerPage, Sort.by("bookPublicationYear")))
                    .getContent();
        else if (sortByYear == null)
            return booksRepository.findAll(PageRequest.of(pageNum, itemsPerPage)).getContent();
        else
            return booksRepository.findAll(PageRequest.of(pageNum, itemsPerPage)).getContent();
    }

    public Book findOne(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        return foundBook.orElse(null);
    }

    public List<Book> showBookList(int personId) {
        return booksRepository.findBooksByOwner(peopleService.findOne(personId));
    }

    public Person findOwner(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        if (foundBook.isPresent()) {
            Person owner = foundBook.get().getOwner();
            if (owner != null) {
                return owner;
            } else return null;
        } else return null;
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int bookId, Book updatedBook) {
        updatedBook.setBookId(bookId);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    @Transactional
    public void freeBook(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        if (foundBook.isPresent()) {
            Person owner = foundBook.get().getOwner();
            owner.getBooks().remove(foundBook);

            foundBook.get().setOwner(null);
        }
    }

    @Transactional
    public void assignBook(Person person, int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setOwner(person);
        }
    }
}
