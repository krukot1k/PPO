package com.example.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class MainUnitTest {

    @Mock
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Mockito.mock(MainActivity.class);
    }

    @Test
    public void factorial_isCorrect() throws Exception {
        mainActivity.factorial(2);
        verify(mainActivity).factorial(2);
    }

    @Test
    public void eval_isCorrect() throws Exception {
        mainActivity.eval("3*7");
        verify(mainActivity).eval("3*7");

        mainActivity.eval("2+4+3");
        verify(mainActivity).eval("2+4+3");
        
        mainActivity.eval("0");
        verify(mainActivity).eval("0");
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}