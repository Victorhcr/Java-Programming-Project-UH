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
     */
    @Test
    public void testReplace() {
        String word = "São!pA:";
        CreateChartActionListener instance = new CreateChartActionListener(null,null,null,null,1);
        String expResult = "SopA";
        String result = instance.replace(word);
        assertEquals(expResult, result);
    }
    
}
