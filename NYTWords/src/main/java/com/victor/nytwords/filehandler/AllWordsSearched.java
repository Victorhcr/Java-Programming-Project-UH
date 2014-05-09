/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.filehandler;

import com.victor.nytwords.abstractclasses.WordHistory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Victorhcr
 */
public final class AllWordsSearched extends WordHistory {

    private HashMap list;
    private String file;

    /**
     * Constructor of History Class
     * @param filePath Path in the computer to the history file
     */
    public AllWordsSearched(String filePath) {
        this.file = filePath;
        this.list = new HashMap<>();
        this.list = getData();
        this.list = sortHashMapByValues(this.list);
    }

    /**
     * Update file of history of words used by users
     * @param word Word wrote by the user
     * @throws IOException 
     */
    @Override
    public void updateWordsStatisticsFile(String word) throws IOException {
        this.list = getData();
        if (!this.list.containsKey(word)) {
            addNewWord(word,this.file);
        } else {
            replaceSelectedWordLine(word, (Integer) this.list.get(word), this.file);
        }
        this.list = sortHashMapByValues(this.list);
    }

    /**
     * Parse the history file
     * @return HashMap with word as key and number of times they
     * were written in the program as value
     */
    public HashMap getData() {
        BufferedReader br = null;
        HashMap words = new HashMap();
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(new FileReader(this.file));

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
