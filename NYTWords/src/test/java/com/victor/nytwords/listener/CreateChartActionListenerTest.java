/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.listener;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victorhcr
 */
public class CreateChartActionListenerTest {
    
    public CreateChartActionListenerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with a tilde
     */
    @Test
    public void wordWithTilde() {
        String word = "sã:";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "s";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with a colon
     */
    @Test
    public void wordWithColon() {
        String word = "s:";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "s";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with apostrophe
     */
    @Test
    public void wordWithApostrophe() {
        String word = "s\"";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "s";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with a capital letter
     */
    @Test
    public void wordWithCapitalLetter() {
        String word = "sA";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "sA";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with Space
     */
    @Test
    public void wordWithSpace() {
        String word = " s b ";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "s b";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of replace method, of class CreateChartActionListener.
     * Word with a plus
     */
    @Test
    public void wordWithPlus() {
        String word = "s+";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "s";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
}
