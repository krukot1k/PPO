package com.example.ipr2database;

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
    public void readDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.readDatabase();
        verify(databaseHelper).readDatabase();
    }

    @Test
    public void takeFromDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.takeFromDatabase(7);
        verify(databaseHelper).takeFromDatabase(7);
    }

    @Test
    public void deleteFromDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.deleteFromDatabase("name");
        verify(databaseHelper).deleteFromDatabase("name");
    }

    @Test
    public void updateDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.updateDatabase("oldName","newName","post", 500);
        verify(databaseHelper).updateDatabase("oldName","newName","post", 500);
    }
    @Test
    public void closeDatabaseTest() {
        DatabaseHelper databaseHelper = Mockito.mock(DatabaseHelper.class);
        databaseHelper.closeDatabase();
        verify(databaseHelper).closeDatabase();
    }
}