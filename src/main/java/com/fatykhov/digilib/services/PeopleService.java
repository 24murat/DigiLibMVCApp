package com.fatykhov.digilib.services;

import com.fatykhov.digilib.models.Book;
import com.fatykhov.digilib.models.Person;
import com.fatykhov.digilib.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int personId) {
        Optional<Person> foundPerson = peopleRepository.findById(personId);

        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int personId, Person updatedPerson) {
        updatedPerson.setPersonId(personId);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int personId) {
        peopleRepository.deleteById(personId);
    }
}
