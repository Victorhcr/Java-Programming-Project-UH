/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class ViewTest {
    
    public ViewTest() {
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
     * Test of addToPanelTop method, of class View.
     */
    @Test
    public void testAddToPanelTop() {
        System.out.println("addToPanelTop");
        JPanel textPanel = null;
        JPanel comboPanel = null;
        JButton createChart = null;
        View instance = new View();
        JPanel expResult = null;
        JPanel result = instance.addToPanelTop(textPanel, comboPanel, createChart);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMostSearchedWords method, of class View.
     */
    @Test
    public void testAddMostSearchedWords() {
        System.out.println("addMostSearchedWords");
        View instance = new View();
        JPanel expResult = null;
        JPanel result = instance.addMostSearchedWords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGuiFrame method, of class View.
     */
    @Test
    public void testSetGuiFrame() {
        System.out.println("setGuiFrame");
        JPanel panelTop = null;
        JPanel panelBottom = null;
        View instance = new View();
        JFrame expResult = null;
        JFrame result = instance.setGuiFrame(panelTop, panelBottom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogTime method, of class View.
     */
    @Test
    public void testGetLogTime() {
        System.out.println("getLogTime");
        View instance = new View();
        int expResult = 0;
        int result = instance.getLogTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMostSearchedWords method, of class View.
     */
    @Test
    public void testUpdateMostSearchedWords() {
        System.out.println("updateMostSearchedWords");
        View instance = new View();
        instance.updateMostSearchedWords();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
