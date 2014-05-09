/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

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
public class ArticleTest {
    
    public ArticleTest() {
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
     * Test of addHits method, of class Article.
     */
    @Test
    public void testAddHits() throws Exception {
        System.out.println("addHits");
        String word = "war";
        int beg = 2000;
        int end = 2000;
        Article instance = new Article(word,beg,end);
        String[] expResult = {"\"A Kashmiri Mystery\"", "\"When Bill Clinton "
                + "went to India in March, it was the first visit by an American"
                + " president in 22 years. Among the careful preparations for "
                + "the historic occasion were a painstaking cleanup around the Taj Mahal,"
                + " a reconnoitering for wild tigers he might...\"",
            "http://www.nytimes.com/2000/12/31/magazine/a-kashmiri-mystery.html"};
        String[] result = instance.addHits(word, beg, end);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getWord method, of class Article.
     */
    @Test
    public void testGetWord() {
        String word = "war";
        int beg = 2000;
        int end = 2000;
        Article instance = new Article(word,beg,end);
        String expResult = "war";
        String result = instance.getWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeg method, of class Article.
     */
    @Test
    public void testGetBeg() {
        String word = "war";
        int beg = 2000;
        int end = 2000;
        Article instance = new Article(word,beg,end);
        int expResult = 2000;
        int result = instance.getBeg();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnd method, of class Article.
     */
    @Test
    public void testGetEnd() {
        String word = "war";
        int beg = 2000;
        int end = 2000;
        Article instance = new Article(word,beg,end);
        int expResult = 2000;
        int result = instance.getEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData method, of class Article.
     */
    @Test
    public void testGetData() throws IOException {
        System.out.println("addHits");
        String word = "war";
        int beg = 2000;
        int end = 2000;
        Article instance = new Article(word,beg,end);
        String[] expResult = {"\"A Kashmiri Mystery\"", "\"When Bill Clinton "
                + "went to India in March, it was the first visit by an American"
                + " president in 22 years. Among the careful preparations for "
                + "the historic occasion were a painstaking cleanup around the Taj Mahal,"
                + " a reconnoitering for wild tigers he might...\"",
            "http://www.nytimes.com/2000/12/31/magazine/a-kashmiri-mystery.html"};
        instance.start();
        String[] result = instance.getData();
        assertArrayEquals(expResult, result);
    }
    
}
