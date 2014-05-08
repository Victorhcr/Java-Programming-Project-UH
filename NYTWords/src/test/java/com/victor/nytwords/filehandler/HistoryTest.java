/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class HistoryTest {
    
    public HistoryTest() {
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
    
    public String readFirstLineFile(String input){
        BufferedReader br = null;
        String result = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(input));

            result = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Test of sortHashMapByValues method, of class AllWordsFile.
     */
    @Test
    public void testSortHashMapByValues() {
        History instance = new History("testfiles/all_words_statistics/notes.txt");
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
        History instance = new History("testfiles/all_words_statistics/notes.txt");
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
        History instance = new History("testfiles/all_words_statistics/notes.txt");
        String expResult = "love";
        String result = instance.getWordByIndexOrder(1);
        assertEquals(expResult, result);
    }
    
}
