/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import com.victor.nytwords.functions.Predictor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author Victorhcr
 */
public class SaveAPIData {
    private HashMap yearHits;
    private String word;
    private int beg;
    private int end;
    
    /**
     * 
     * @param word Word wrote by the user
     * @param beg First year chosen by the user
     * @param end Last year chosen by the user
     * @param years HashMap containing the percentage of articles for each year
     */
    public SaveAPIData(String word, int beg, int end, HashMap years){
        this.yearHits = years;
        this.beg = beg;
        this.end = end;
        this.word = word;
    }
    
    /**
     * Writes prediction file based on data from NYTimes API
     * @throws java.io.IOException Something interrupts I/O
     */
    public void writeFilePred() throws IOException{
        FileWriter file = new FileWriter("files/words_data/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        Predictor predictor = new Predictor(this.yearHits);
        file.append(String.format(Locale.US, "%.0f", predictor.max()) + ": " + String.format(Locale.US, "%.2f", this.yearHits.get(predictor.max())) + "\n");
        file.append(String.format(Locale.US, "%.0f", predictor.max()+1) + ": " + String.format(Locale.US, "%.2f", predictor.predict()) + "\n");
        file.append("-");
        file.close();
    }
    
    /**
     * Write data parsed from NYTimes API 
     * @throws java.io.IOException Something interrupts I/O
     */
    public void writeFile() throws IOException{
        FileWriter file = new FileWriter("files/words_data/" + this.word + "-" + this.beg + "-" + this.end + ".txt"); 
        List sortedKeys = new ArrayList(this.yearHits.keySet());
        Collections.sort(sortedKeys);
        for(Object year : sortedKeys){
            file.append(String.format(Locale.US, "%.0f", year) + ": " + String.format(Locale.US, "%.2f", this.yearHits.get(year)) + "\n");
        }
        file.append("-"); 
        file.close();
    }
    
    /**
     * Write Main file
     * @throws java.io.IOException Something interrupts I/O
     */
    public void writeFileMain() throws IOException{
        FileWriter main = new FileWriter("files/words_data/main.txt");
        main.append(word + "\n");
        main.append(beg + "\n");
        main.append(end + "\n");
        main.close();
    }
}
