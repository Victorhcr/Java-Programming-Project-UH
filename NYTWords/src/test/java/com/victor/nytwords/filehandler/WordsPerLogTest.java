/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

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
public class WordsPerLogTest {
    
    public WordsPerLogTest() {
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
     * Test of sortHashMapByValues method, of class AllWordsFile.
     */
    @Test
    public void testSortHashMapByValues() {
        WordsPerLog instance = new WordsPerLog(2,"testfiles/history/");
        HashMap words = new HashMap();
        words.put("war", 1);
        words.put("love", 2);
        
        HashMap hashSorted = instance.sortHashMapByValues(words);
        String result = null;
        for(Object word: hashSorted.keySet()){
            result = (String) word;
            break;
        }
        assertEquals("love", result);
    }

    /**
     * Test of getData method, of class AllWordsFile.
     */
    @Test
    public void testGetData() {
        HashMap words = new HashMap();
        AllWordsSearched instance = new AllWordsSearched("testfiles/all_words_statistics/notes.txt");
        words = instance.getData();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(1, words.get("war"));
    }

    /**
     * Test of getIndexOrdered method, of class AllWordsFile.
     */
    @Test
    public void testGetIndexOrdered() {
        int i = 1;
        AllWordsSearched instance = new AllWordsSearched("testfiles/all_words_statistics/notes.txt");
        String expResult = "love";
        String result = instance.getWordByIndexOrder(1,instance.getList());
        assertEquals(expResult, result);
    }
    
}
