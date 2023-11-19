package com.example.kr2;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;


public class DatabaseHelperTest {
    @Test
    public void openDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.openDatabase();
        verify(databaseHelper).openDatabase();
    }
    @Test
    public void checkUserTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.checkUser("user");
        verify(databaseHelper).checkUser("user");
    }
    @Test
    public void readDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.readDatabase();
        verify(databaseHelper).readDatabase();
    }
    @Test
    public void updateDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.updateDatabase(0,"user", 1000);
        verify(databaseHelper).updateDatabase(0,"user", 1000);
    }
    @Test
    public void closeDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.closeDatabase();
        verify(databaseHelper).closeDatabase();
    }
}