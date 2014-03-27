/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.util.Collection;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Victorhcr
 */
public class LinRegTest {
    private HashMap hits;
    private LinReg lr;
    
    public LinRegTest(){
        this.hits = new HashMap<Double,Double>();
        hits.put(2001.0, 1.0);
        hits.put(2002.0, 2.0);
        hits.put(2003.0, 3.0);
        
        this.lr = new LinReg(this.hits);
    }
    
    /**
     * Test of averageNormal method, of class LinReg.
     */
    @Test
    public void testAverageNormal() {
        double expResult = 2.0;
        double result = this.lr.averageNormal(this.hits.values());
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of averageSquare method, of class LinReg.
     */
    @Test
    public void testaverageSquare() {
        double expResult = 4.66;
        double result = this.lr.averageSquare(this.hits.values());
        assertEquals(expResult, result, 0.01);
    }
    
    /**
     * Test of averageMult method, of class LinReg.
     */
    @Test
    public void testaverageMult() {
        double expResult = 4004.66;
        double result = this.lr.averageMult();
        assertEquals(expResult, result, 0.01);
    }
    
    /**
     * Test of averageMult method, of class LinReg.
     */
    @Test
    public void max() {
        double expResult = 2003.0;
        double result = this.lr.max();
        assertEquals(expResult, result, 0.00);
    }
    
}
