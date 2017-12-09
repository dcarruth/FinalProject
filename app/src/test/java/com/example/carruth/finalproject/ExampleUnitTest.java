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
    public void checkIfEmpty()throws Exception{
        assertFalse(new CreateAccountActivity().everythingFilled("d","d","d","d","","d","d","d",""));
        assertTrue(new CreateAccountActivity().everythingFilled("d","d","d","d","d","d","david@asd.com","d","d..ads"));
        assertFalse(new CreateAccountActivity().everythingFilled("","d","","d","","d","d","d",""));
        assertTrue(new CreateAccountActivity().everythingFilled("!@#%","123sd","d","d","a","d","d","123","1"));
        assertFalse(new CreateAccountActivity().everythingFilled("","","","","","","","",""));
    }

}