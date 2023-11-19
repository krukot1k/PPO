package com.example.ipr2database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;


public class ChangeTest {
    @Test
    public void initTest() {
        ChangeActivity changeActivity = Mockito.mock(ChangeActivity.class);
        changeActivity.backToList();
        verify(changeActivity).backToList();
    }
}