package com.fatykhov.digilib.models;

public class Person {
    private int personId;
    private String personName;
    private int personYearOfBirth;

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
}
