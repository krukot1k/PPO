package com.example.kr2;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;


public class StartTest {
    @Test
    public void initTest() {
        StartActivity startActivity = Mockito.mock(StartActivity.class);
        startActivity.checkText();
        verify(startActivity).checkText();
    }
}