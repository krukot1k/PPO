package com.example.ipr2database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PersonTest {
    @Test
    public void constructorTest() {
        Person person = new Person("Eugene", "CEO", 10000);
        assertEquals("Eugene", person.getName());
        assertEquals("CEO",person.getPost());
        assertEquals(10000,person.getSalary());
    }
    @Test
    public void setUsernameTest() {
        Person person = new Person("", "example", 777);
        person.setName("Eugene");
        assertEquals("Eugene", person.getName());
    }
}