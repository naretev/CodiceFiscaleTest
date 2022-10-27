package org.example;

public class Person {
    public String surname;
    public String name;
    public String gender;
    public String dateOfBirth;

    public Person(String name, String surname, String gender, String dateOfBirth) {
        this.name = name.toLowerCase();
        this.surname = surname.toLowerCase();
        this.gender = gender.toLowerCase();
        this.dateOfBirth = dateOfBirth.toLowerCase();
    }
}
