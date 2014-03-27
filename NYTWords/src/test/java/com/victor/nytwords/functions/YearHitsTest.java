/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Victorhcr
 */
public class YearHitsTest {
    
    public ArrayList getPublicMethods(){
        ArrayList<String> met = new ArrayList<String>();
        met.add("getWord");
        met.add("start");
        met.add("getBeg");
        met.add("getEnd");
        met.add("getMap");
        
        return met;
    }
    
    public ArrayList<String> checkMethods(ArrayList<String> allMethods, Class c){
        ArrayList<String> ar = new ArrayList();
        boolean hasMethod;
        Method[] methods = c.getMethods();
        for (String s : allMethods){
            hasMethod = false;
            for (Method m : methods) {
                if (m.getName().equals(s)) {
                  hasMethod = true;
                  break;
                }
            }
            if(!hasMethod){
                ar.add("false");
                ar.add(s);
                return ar;
            }
        }
        ar.add("true");
        ar.add("");
        return ar;
    }
    
    @Test
    public void testHasAllPublicMethods() {
        ArrayList<String> allMeth = getPublicMethods();
        ArrayList<String> result = new ArrayList();
        
        boolean hasMethod;
        String nameM = "";
        YearHits yh = new YearHits();
        Class c = yh.getClass();
        hasMethod = false;
        
        result = checkMethods(allMeth,c);
        
        assertEquals("Method " + result.get(1) + " is missing.", true, Boolean.parseBoolean(result.get(0)));
    }
    
    /* One word and final year less than first year  */
    @Test
    public void testAddHits1() {
        YearHits yh = new YearHits();
        String word = "war";
        int beg = 2013;
        int end = 2012;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2013.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(0, total,0.000001);
    }
    
    /* One word and one year */
    @Test
    public void testAddHits2() {
        YearHits yh = new YearHits();
        String word = "war";
        int beg = 2013;
        int end = 2013;
        
        double total = 0;
        try {
            total = (Double) yh.addHits(word, beg, end).get(2013.0);
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(20.955558814901732,total,0.000000000000001);
    }
    
    /* Another word and one year */
    @Test
    public void testAddHits3() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2005;
        int end = 2005;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2005.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(9.049244271087275, total,0.000000000000000001);
    }
    
    /* Empty word and random range of years */
    @Test
    public void testAddHits4() {
        YearHits yh = new YearHits();
        String word = "";
        int beg = 2003;
        int end = 2005;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2013.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(0,total,0.000001);
    }
    
    /* One word and two years getting the first year */
    @Test
    public void testAddHits5() {
        YearHits yh = new YearHits();
        String word = "war";
        int beg = 2000;
        int end = 2001;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2000.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(21.659712563745945, total,0.0000000000000001);
    }
    
    /* Another word and two years getting the last year */
    @Test
    public void testAddHits6() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2000;
        int end = 2001;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2001.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(8.486662558323797, total,0.0000000000001);
    }
    
    /* Non existent word and random range of years */
    @Test
    public void testAddHits7() {
        YearHits yh = new YearHits();
        String word = "oishfoihefoiew";
        int beg = 2001;
        int end = 2001;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2001.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(0, total,0.00001);
    }
    
    /* One word and a non existent years */
    @Test
    public void testAddHits8() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2004902;
        int end = 3091283;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2013.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(0, total,0.00001);
    }
    
    /* Number as word and an existing year */
    @Test
    public void testAddHits9() {
        YearHits yh = new YearHits();
        String word = "9023740";
        int beg = 2013;
        int end = 2013;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2013.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(0, total,0.0001);
    }
    
    /* Word with space and an existing year */
    @Test
    public void testAddHits10() {
        YearHits yh = new YearHits();
        String word = "war love";
        int beg = 2013;
        int end = 2013;
        
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(2013.0).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(1.7233792901144032, total,0.00000000000001);
    }
    
    
    
}
