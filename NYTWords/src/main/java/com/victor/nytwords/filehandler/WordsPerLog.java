/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.filehandler;

import com.victor.nytwords.abstractclasses.WordHistory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victorhcr
 */
public final class WordsPerLog extends WordHistory {

    private HashMap list;
    private final int logTime;
    private final String file;

    /**
     * Constructor of WordsPerLog class
     * @param input Word written in the program
     * @param file File path to the history folder
     */
    public WordsPerLog(int input, String file) {
        this.file = file;
        this.logTime = input;
        this.list = new HashMap<>();
        try {
            createDoc(this.logTime + "");
        } catch (IOException ex) {
            Logger.getLogger(WordsPerLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update file of log of words used by users
     * @param word Word wrote by the user
     * @throws java.io.IOException Something interrupts I/O
     */
    @Override
    public void updateWordsStatisticsFile(String word) throws IOException {
        this.list = getData(this.logTime);
        if (!this.list.containsKey(word)) {
            addNewWord(word,this.file + "log" + this.logTime + ".txt");
        } else {
            replaceSelectedWordLine(word, (Integer) this.list.get(word),this.file + "log" + this.logTime + ".txt");
        }
        this.list = sortHashMapByValues(this.list);
    }
    
    /**
     * Create log file document 
     * @param number Number of times the program was initialized
     * @throws java.io.IOException Something interrupts I/O
     */
    public void createDoc(String number) throws IOException{
        Writer output;
        output = new BufferedWriter(new FileWriter(this.file + "log" + number + ".txt", true));
        output.close();
    }

    /**
     * Parse log file in the history folder
     * @param logTime The time the program was used
     * @return HashMap with words as key and the number of times
     * they were input in the program as value 
     */
    public HashMap getData(int logTime) {
        BufferedReader br = null;
        HashMap words = new HashMap();
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(new FileReader(this.file + "log" + logTime + ".txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                parts = sCurrentLine.split(": ");
                words.put(parts[0], Integer.parseInt(parts[1]));
            }
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
        return words;
    }

    /**
     * Returns list of words
     * @return List of words with number of times user
     */
    public HashMap getList(){
        return this.list;
    }
}

