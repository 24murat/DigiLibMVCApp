package com.fatykhov.digilib.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "personId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "personName")
    private String personName;

    @Min(value = 1900, message = "Year of birth is incorrect")
    @Max(value = 2023, message = "Year of birth is incorrect")
    @Column(name = "personYearOfBirth")
    private int personYearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(int personId, String personName, int personYearOfBirth) {
        this.personId = personId;
        this.personName = personName;
        this.personYearOfBirth = personYearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonYearOfBirth() {
        return personYearOfBirth;
    }

    public void setPersonYearOfBirth(int personYearOfBirth) {
        this.personYearOfBirth = personYearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personYearOfBirth=" + personYearOfBirth +
                '}';
    }
}
