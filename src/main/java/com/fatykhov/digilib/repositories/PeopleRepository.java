package com.fatykhov.digilib.repositories;

import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
