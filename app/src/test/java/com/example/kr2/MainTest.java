package com.example.kr2;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;


public class MainTest {
    @Test
    public void initTest() {
        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        mainActivity.init();
        verify(mainActivity).init();
    }

    @Test
    public void toStartTest() {
        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        mainActivity.toStart();
        verify(mainActivity).toStart();
    }

    @Test
    public void hitTargetTest() {
        MainActivity mainActivity = Mockito.mock(MainActivity.class);
        mainActivity.hitTarget();
        verify(mainActivity).hitTarget();
    }
}