/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import com.victor.nytwords.functions.LinReg;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Victorhcr
 */
public class FileHandle {
    private HashMap yearHits;
    private String word;
    private int beg;
    private int end;
    
    public FileHandle(String word, int beg, int end, HashMap years){
        this.yearHits = years;
        this.beg = beg;
        this.end = end;
        this.word = word;
    }
    
    /**
     * Writes prediction file based on data from NYTimes API
     * @throws IOException 
     */
    public void writeFilePred() throws IOException{
        FileWriter file = new FileWriter("files/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        LinReg lr = new LinReg(this.yearHits);
        file.append(String.format( "%.0f", lr.max()) + ": " + String.format( "%.2f", this.yearHits.get(lr.max())) + "\n");
        file.append(String.format( "%.0f", lr.max()+1) + ": " + String.format( "%.2f", lr.predict()) + "\n");
        file.append("-");
        file.close();
    }
    
    /**
     * Write data parsed from NYTimes API
     * @throws IOException 
     */
    public void writeFile() throws IOException{
        FileWriter file = new FileWriter("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt"); 
        List sortedKeys = new ArrayList(this.yearHits.keySet());
        Collections.sort(sortedKeys);
        for(Object year : sortedKeys){
            file.append(String.format( "%.0f", year) + ": " + String.format( "%.2f", this.yearHits.get(year)) + "\n");
        }
        file.append("-"); 
        file.close();
    }
    
    /**
     * Write Main file
     * @throws IOException 
     */
    public void writeFileMain() throws IOException{
        FileWriter main = new FileWriter("files/main.txt");
        main.append(word + "\n");
        main.append(beg + "\n");
        main.append(end + "\n");
        main.close();
    }
}
