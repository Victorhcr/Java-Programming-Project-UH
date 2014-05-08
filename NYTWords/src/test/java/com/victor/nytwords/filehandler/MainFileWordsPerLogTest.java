/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class MainFileWordsPerLogTest {
    
    public MainFileWordsPerLogTest() {
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
     * Test of getData method, of class MainLogWordsFile.
     */
    @Test
    public void testGetData() throws FileNotFoundException {
        MainFileWordsPerLog instance = new MainFileWordsPerLog("testfiles/history/main.txt");
        int result = instance.getData(new FileReader("testfiles/history/main.txt"));
        int expResult = 3;
        assertEquals(expResult, result);
    }


    /**
     * Test of getLog method, of class MainLogWordsFile.
     */
    @Test
    public void testGetLog() throws FileNotFoundException, IOException {
        MainFileWordsPerLog instance = new MainFileWordsPerLog("testfiles/history/main.txt");
        instance.start();
        int result = instance.getLog();
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
}
