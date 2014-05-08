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
public class MainFileWordsPerLog {
    private int logTime;
    private String file;
    
    /**
     * Constructor of MainFileWordsPerLog class
     * @param input File path to the main file
     */
    public MainFileWordsPerLog(String input){
        this.file = input;
    }
    
    /**
     * Starts to parse the file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void start() throws FileNotFoundException, IOException{
        this.logTime = getData(new FileReader(this.file));
        writeFileMain();
    }
    
    /**
     * This method gets data from file in user computer and gets the number of
     * times the program was used
     * @param main FileReader parameter to read file
     */
    public int getData(FileReader main) {
        BufferedReader br = null;
        int logTime = -1;
        try {
            String sCurrentLine;
            br = new BufferedReader(main);

            logTime = Integer.parseInt(br.readLine());

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
        return logTime;
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
    
    /**
     * Gets the number of times the program was used
     * @return Return the number of times the program was used
     */
    public int getLog(){
        return this.logTime;
    }
}
