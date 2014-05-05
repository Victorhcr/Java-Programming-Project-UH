/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.filehandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Victorhcr
 */
public class MainLogWordsFile {
    private int logTime;
    
    public MainLogWordsFile(){
        
    }
    
    public void start() throws FileNotFoundException, IOException{
        getData(new FileReader("files/history/main.txt"));
        writeFileMain();
    }
    
    /**
     * This method gets data from file in user computer and check which file to
     * parse
     *
     * @param main
     */
    public void getData(FileReader main) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(main);

            this.logTime = Integer.parseInt(br.readLine());

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
    }
    
    /**
     * Write Main file
     * @throws IOException 
     */
    public void writeFileMain() throws IOException{
        FileWriter main = new FileWriter("files/history/main.txt");
        main.append((this.logTime+1) + "");
        main.close();
    }
    
    public int getLog(){
        return this.logTime;
    }
}
