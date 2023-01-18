package com.example.ipr1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.verify

@RunWith(JUnit4.class)
public class MainUnitTest {

    @Mock
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Mockito.mock(MainActivity.class);
    }

    @Test
    public void createSensors_isCorrect() throws Exception {
        mainActivity.createSensors();
        verify(mainActivity).createSensors();
    }

    @Test
    public void isTarget_isCorrect() throws Exception {
        mainActivity.isTarget();
        verify(mainActivity).isTarget();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}
