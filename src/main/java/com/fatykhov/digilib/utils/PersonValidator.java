package com.fatykhov.digilib.utils;

import com.fatykhov.digilib.dao.PersonDAO;
import com.fatykhov.digilib.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getPersonName()).isPresent()) {
            errors.rejectValue("personName", "", "Человек с таким именем уже есть в базе");
        }

        if (person.getPersonYearOfBirth() < 1900 || person.getPersonYearOfBirth() > LocalDate.now().getYear()) {
            errors.rejectValue("personYearOfBirth", "", "Введен некорректный год рождения");
        }

        if (person.getPersonName().equals("")) {
            errors.rejectValue("personName", "", "Имя не может быть пустым");
        }
    }
}
