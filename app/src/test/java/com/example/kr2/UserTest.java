package com.example.kr2;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void constructorTest() {
        User user = new User(7, "usersss", 123);
        assertEquals(7, user.getId());
        assertEquals("usersss", user.getUsername());
        assertEquals(123, user.getScore());
    }
    @Test
    public void setIdTest() {
        User user = new User(0, "username", 1000);
        user.setId(10);
        assertEquals(10, user.getId());
    }
    @Test
    public void setUsernameTest() {
        User user = new User(0, "username", 1000);
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }
    @Test
    public void setScoreTest() {
        User user = new User(0, "username", 1000);
        user.setScore(999);
        assertEquals(999, user.getScore());
    }
}