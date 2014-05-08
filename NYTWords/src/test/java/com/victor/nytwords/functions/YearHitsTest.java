/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
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
    public void hasAllPublicMethods() {
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
    
    public void calculate(String word, int beg, int end, double get, double expected){
        YearHits yh = new YearHits();
        double total = 0;
        try {
            total = Double.parseDouble(yh.addHits(word, beg, end).get(get).toString());
        } catch (Exception ex) {
            Logger.getLogger(YearHitsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(expected, total,0.0000000001);
    }
    
    /* One word and final year less than first year  */
    @Test
    public void oneWordFinalYearLessFirstYear() {
        String word = "war";
        int beg = 2013;
        int end = 2012;
        double get = 2013.0;
        calculate(word, beg, end, get,0);
    }
    
    /* One word and one year */
    @Test
    public void oneWordOneYear() {
        YearHits yh = new YearHits();
        String word = "war";
        int beg = 2013;
        int end = 2013;
        double get = 2013.0;
        calculate(word, beg, end, get,20.92321617136823);
    }
    
    /* Another word and one year */
    @Test
    public void differentWordOneYear() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2005;
        int end = 2005;
        double get = 2005.0;
        calculate(word, beg, end, get,9.041279570922326);
    }
    
    /* Empty word and random range of years */
    @Test
    public void emptyWordRandomRangeYears() {
        YearHits yh = new YearHits();
        String word = "";
        int beg = 2003;
        int end = 2005;
        double get = 2013.0;
        calculate(word, beg, end, get,0);
    }
    
    /* One word and two years getting the first year */
    @Test
    public void oneWordTwoYearsGettingFirstYear() {
        YearHits yh = new YearHits();
        String word = "war";
        int beg = 2000;
        int end = 2001;
        double get = 2000.0;
        calculate(word, beg, end, get,21.31860170259011);
    }
    
    /* Another word and two years getting the last year */
    @Test
    public void anotherWordTwoYearsGettingLastYear() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2000;
        int end = 2001;
        double get = 2001.0;
        calculate(word, beg, end, get,8.336929317338397);
    }
    
    /* Non existent word and random range of years */
    @Test
    public void nonExistingWordRandomRangeYears() {
        YearHits yh = new YearHits();
        String word = "oishfoihefoiew";
        int beg = 2001;
        int end = 2001;
        double get = 2001.0;
        calculate(word, beg, end, get,0);
    }
    
    /* One word and a non existent years */
    @Test
    public void oneWordNonExistingYears() {
        YearHits yh = new YearHits();
        String word = "love";
        int beg = 2004902;
        int end = 3091283;
        double get = 2013.0;
        calculate(word, beg, end, get,0);
    }
    
    /* Number as word and an existing year */
    @Test
    public void numberAsWordExistingYear() {
        YearHits yh = new YearHits();
        String word = "9023740";
        int beg = 2013;
        int end = 2013;
        double get = 2013.0;
        calculate(word, beg, end, get,0);
    }
    
    /* Word with space and an existing year */
    @Test
    public void wordWithSpaceExistingYear() {
        YearHits yh = new YearHits();
        String word = "war love";
        int beg = 2013;
        int end = 2013;
        double get = 2013.0;
        calculate(word, beg, end, get,1.5688640820636597);
    }
    
    /* Word with space and an existing year */
    @Test
    public void getWord() {
        YearHits yh = new YearHits("war", 2010,2013);
        assertEquals("war", yh.getWord());
    }
    
    /* Word with space and an existing year */
    @Test
    public void getBeg() {
        YearHits yh = new YearHits("war", 2010,2013);
        assertEquals(2010, yh.getBeg());
    }
    
    /* Word with space and an existing year */
    @Test
    public void getEnd() {
        YearHits yh = new YearHits("war", 2010,2013);
        assertEquals(2013, yh.getEnd());
    }
    
    /* Word with space and an existing year */
    @Test
    public void getMap() throws Exception {
        YearHits yh = new YearHits("war", 2013,2013);
        HashMap result = new HashMap();
        result.put(2013.0, 20.92321617136823);
        assertEquals(result, yh.addHits("war", 2013, 2013));
    }
}
