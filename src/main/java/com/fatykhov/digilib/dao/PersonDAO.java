//package com.fatykhov.digilib.dao;
//
//import com.fatykhov.digilib.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int personId) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new Object[]{personId},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public Optional<Person> show(String personName) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE personName=?", new Object[]{personName},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(personName, personYearOfBirth) VALUES (?, ?)",
//                person.getPersonName(), person.getPersonYearOfBirth());
//    }
//
//    public void update(int personId, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET personName=?, personYearOfBirth=? WHERE personId=?",
//                updatedPerson.getPersonName(), updatedPerson.getPersonYearOfBirth(), personId);
//    }
//
//    public void delete(int personId) {
//        jdbcTemplate.update("DELETE FROM Person WHERE personId=?", personId);
//    }
//}
