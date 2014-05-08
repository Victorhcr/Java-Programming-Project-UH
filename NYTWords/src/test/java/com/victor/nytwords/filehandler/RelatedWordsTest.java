/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
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
    public void testGetRelatedWords() throws FileNotFoundException {
        System.out.println("getRelatedWords");
        RelatedWords instance = new RelatedWords("war", "testfiles/history/main.txt");
        HashMap wordsRelated = instance.getRelatedWords();
        String result = null;
        for(Object word: wordsRelated.keySet()){
            result = (String) word;
            break;
        }
        assertEquals("Brazil", result);
    }

    /**
     * Test of updateRelatedWords method, of class RelatedWords.
     */
    @Test
    public void testUpdateRelatedWords() throws FileNotFoundException {
        System.out.println("sortHashMapByValues");
        
        HashMap passedMap = new HashMap();
        passedMap.put("war", 1);
        passedMap.put("love", 2);
        
        HashMap resultMap = new HashMap();
        resultMap.put("Brazil", 1);
        resultMap.put("war", 2);
        
        RelatedWords instance = new RelatedWords("war", "testfiles/history/main.txt");
        resultMap = instance.updateRelatedWords(passedMap, resultMap);
        
        assertEquals(3, resultMap.get("war"));
    }

    /**
     * Test of getNumberDocuments method, of class RelatedWords.
     */
    @Test
    public void testGetNumberDocuments() throws FileNotFoundException {
        System.out.println("getRelatedWords");
        RelatedWords instance = new RelatedWords("war", "testfiles/history/main.txt");
        int result = instance.getNumberDocuments(new FileReader("testfiles/history/main.txt"));
        assertEquals(3, result);
    }

    /**
     * Test of getData method, of class RelatedWords.
     */
    @Test
    public void testGetData() throws FileNotFoundException {
        System.out.println("getRelatedWords");
        int logTime = 2;
        RelatedWords instance = new RelatedWords("war", "testfiles/history/main.txt");
        HashMap wordsRelated = instance.getData(logTime);
        String result = null;
        for(Object word: wordsRelated.keySet()){
            result = (String) word;
            break;
        }
        assertEquals("Brazil", result);
    }

    /**
     * Test of sortHashMapByValues method, of class RelatedWords.
     */
    @Test
    public void testSortHashMapByValues() throws FileNotFoundException {
        System.out.println("sortHashMapByValues");
        
        HashMap passedMap = new HashMap();
        HashMap resultMap = new HashMap();
        passedMap.put("war", 1);
        passedMap.put("love", 2);
        
        
        RelatedWords instance = new RelatedWords("war", "testfiles/history/main.txt");
        resultMap = instance.sortHashMapByValues(passedMap);
        String result = null;
        for(Object word: resultMap.keySet()){
            result = (String) word;
            break;
        }
        assertEquals("love", result);
    }
    
}
