/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.util.Collection;
import java.util.HashMap;

/**
 * Predict the next point in a data set
 * @author Victor Rodrigues
 */
public class LinReg {
    private HashMap<Double,Double> points;
    
    public LinReg(HashMap hm){
        this.points = hm;
    }
    
    /**
     * Calculate the average of numbers
     * @param set
     * @return 
     */
    public double averageNormal(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d;
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate the average of squared numbers
     * @param set
     * @return 
     */
    public double averageSquare(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d*d;
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate the average of multiplication of
     * x coordinate times respective y coordinate
     * @return 
     */
    public double averageMult(){
        double total = 0;
        int cnt = 0;
        for(Double d : this.points.keySet()){
            total += d*this.points.get(d);
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate angular coefficient (slope) of prediction line 
     * @return 
     */
    public double angCoef(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        double avgSX = averageSquare(this.points.keySet());
        double avgMXY = averageMult();
        
        double num = avgNX*avgNY - avgMXY;
        double den = avgNX*avgNX -avgSX;
        
        return num/den;
    }
    
    /**
     * Calculate linear coefficient (value of y when x = 0) 
     * of prediction line
     * @return 
     */
    public double linCoef(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        
        double b = avgNY - angCoef()*avgNX;
        
        return b;
    }
    
    /**
     * Find the maximum x value of the data
     * @return 
     */
    public double max(){
        double max = 0;
        int cnt = 0;
        for(double d : this.points.keySet()){
            if(d > max || cnt == 0){
                max = d;
            }
            cnt++;
        }
        return max;
    }
    
    /**
     * Predict y value of next year
     * @return 
     */
    public double predict(){
        return (max()+1)*angCoef()+linCoef();
    }
}
