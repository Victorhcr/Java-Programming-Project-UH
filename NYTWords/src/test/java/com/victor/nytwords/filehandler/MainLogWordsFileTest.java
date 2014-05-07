/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.FileReader;
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
public class MainLogWordsFileTest {
    
    public MainLogWordsFileTest() {
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
     * Test of start method, of class MainLogWordsFile.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        MainLogWordsFile instance = new MainLogWordsFile();
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class MainLogWordsFile.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        FileReader main = null;
        MainLogWordsFile instance = new MainLogWordsFile();
        instance.getData(main);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeFileMain method, of class MainLogWordsFile.
     */
    @Test
    public void testWriteFileMain() throws Exception {
        System.out.println("writeFileMain");
        MainLogWordsFile instance = new MainLogWordsFile();
        instance.writeFileMain();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLog method, of class MainLogWordsFile.
     */
    @Test
    public void testGetLog() {
        System.out.println("getLog");
        MainLogWordsFile instance = new MainLogWordsFile();
        int expResult = 0;
        int result = instance.getLog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
