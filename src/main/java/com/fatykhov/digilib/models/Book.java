package com.fatykhov.digilib.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "bookId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "bookTitle")
    private String bookTitle;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Min(value = 1500, message = "Year of publication is incorrect")
    @Max(value = 2023, message = "Year of publication is incorrect")
    @Column(name = "bookPublicationYear")
    private int bookPublicationYear;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "personId")
    private Person owner;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;

    @Transient
    private boolean isExpired;

    public Book() {
    }

    public Book(int bookId, String bookTitle, String bookAuthor, int bookPublicationYear) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublicationYear = bookPublicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPublicationYear() {
        return bookPublicationYear;
    }

    public void setBookPublicationYear(int bookPublicationYear) {
        this.bookPublicationYear = bookPublicationYear;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublicationYear=" + bookPublicationYear +
                '}';
    }
}
