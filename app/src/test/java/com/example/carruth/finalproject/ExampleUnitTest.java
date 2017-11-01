package com.example.carruth.finalproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void passwordsMatchTest() throws Exception {
        assertTrue(new CreateAccountActivity().checkPass("Game","Match"));
    }

    @Test
    public void checkIfEmpty()throws Exception{
        assertTrue(new CreateAccountActivity().everythingFilled("d","d","d","d","","d","d","d"));
    }

    @Test
    public void checkValidEmail()throws Exception{
        assertTrue(new CreateNewPasswordActivity().validEmail("d"));
    }
}