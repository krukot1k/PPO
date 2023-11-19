package com.example.ipr2database;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;


public class ListTest {
    @Test
    public void toChangeTest() {
        ListActivity listActivity = Mockito.mock(ListActivity.class);
        listActivity.toChange();
        verify(listActivity).toChange();
    }
}