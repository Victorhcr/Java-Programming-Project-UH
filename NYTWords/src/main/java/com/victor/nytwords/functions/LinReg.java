/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Victorhcr
 */
public class LinReg {
    private HashMap<Double,Double> points;
    
    public LinReg(HashMap hm){
        this.points = hm;
    }
    
    public double averageNormal(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d;
            cnt++;
        }
        return total/cnt;
    }
    
    public double averageSquare(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d*d;
            cnt++;
        }
        return total/cnt;
    }
    
    public double averageMult(){
        double total = 0;
        int cnt = 0;
        for(Double d : this.points.keySet()){
            total += d*this.points.get(d);
            cnt++;
        }
        return total/cnt;
    }
    
    public double angCoef(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        double avgSX = averageSquare(this.points.keySet());
        double avgMXY = averageMult();
        
        double num = avgNX*avgNY - avgMXY;
        double den = avgNX*avgNX -avgSX;
        
        return num/den;
    }
    
    public double b(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        
        double b = avgNY - angCoef()*avgNX;
        
        return b;
    }
    
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
    
    public double predict(){
        return (max()+1)*angCoef()+b();
    }
    
    
}
