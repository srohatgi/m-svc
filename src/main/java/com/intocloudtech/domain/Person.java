package com.intocloudtech.domain;

/**
 * Created by sumeet on 6/14/15.
 */
public class Person {
    private String id;
    private String firstName, lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
    }
}
