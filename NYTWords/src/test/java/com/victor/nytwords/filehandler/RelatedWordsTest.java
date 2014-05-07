/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class RelatedWordsTest {
    
    public RelatedWordsTest() {
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
     * Test of getRelatedWords method, of class RelatedWords.
     */
    @Test
    public void testGetRelatedWords() {
        System.out.println("getRelatedWords");
        RelatedWords instance = null;
        HashMap expResult = null;
        HashMap result = instance.getRelatedWords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRelatedWords method, of class RelatedWords.
     */
    @Test
    public void testUpdateRelatedWords() {
        System.out.println("updateRelatedWords");
        HashMap<String, Integer> parsedWords = null;
        HashMap<String, Integer> result_2 = null;
        RelatedWords instance = null;
        HashMap expResult = null;
        HashMap result = instance.updateRelatedWords(parsedWords, result_2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberDocuments method, of class RelatedWords.
     */
    @Test
    public void testGetNumberDocuments() {
        System.out.println("getNumberDocuments");
        FileReader main = null;
        RelatedWords instance = null;
        int expResult = 0;
        int result = instance.getNumberDocuments(main);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class RelatedWords.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        int logTime = 0;
        RelatedWords instance = null;
        HashMap expResult = null;
        HashMap result = instance.getData(logTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortHashMapByValues method, of class RelatedWords.
     */
    @Test
    public void testSortHashMapByValues() {
        System.out.println("sortHashMapByValues");
        HashMap passedMap = null;
        RelatedWords instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.sortHashMapByValues(passedMap);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
