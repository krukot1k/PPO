package com.example.ipr2database;

public class Person {
    private String name;
    private String post;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost() {
        this.post = post;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary() {
        this.salary = salary;
    }

    public Person(String name, String post, int salary) {
        this.name = name;
        this.post = post;
        this.salary = salary;
    }
}
