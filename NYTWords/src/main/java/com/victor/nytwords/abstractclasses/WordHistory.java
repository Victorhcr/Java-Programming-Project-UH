/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.abstractclasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Victorhcr
 */
public abstract class WordHistory {
    
    /**
     * Update the number of times the word input by the user was written in the
     * program in the history file
     * @param word Word wrote by the user
     * @param times Times the word was written in the program
     * @param filePath Path to file in the computer
     */
    public void replaceSelectedWordLine(String word, Integer times, String filePath) {
        try {
            // input the file content to the String "input"
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            String line;
            String input = "";

            while ((line = file.readLine()) != null) {
                input += line + '\n';
            }

            // this if structure determines whether or not to replace
            input = input.replace(word + ": " + times, word + ": " + (times + 1));

            // write the new String with the replaced line OVER the same file
            FileOutputStream File = new FileOutputStream(filePath);
            File.write(input.getBytes());

        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }
    
    /**
     * Update file of history of words used by users
     * @param word Word wrote by the user
     * @throws java.io.IOException Something interrupts I/O
     */
    abstract public void updateWordsStatisticsFile(String word) throws IOException;
    
    /**
     * Sort the HashMap of passedMap according to the value
     * @param passedMap HashMap of words and times they were requested by users
     * @return Sorted HashMap by the value
     */
    public LinkedHashMap sortHashMapByValues(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.reverse(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String) key, (Integer) val);
                    break;
                }
            } 
        }
        return sortedMap;
    }
    
    /**
     * Add word not used before to the history file
     * @param word Word wrote by the user
     * @param filePath Path in the computer to the history file
     * @throws java.io.IOException Something interrupts I/O
     */
    public void addNewWord(String word, String filePath) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(word + ": 1" + "\n");
        output.close();
    }
    
    /**
     * Gets the the i-th most written word in the program
     * @param i Position of word in the sorted by value HashMap
     * @param list List of words with number of times used in the program
     * @return Word ranked in the i position
     */
    public String getWordByIndexOrder(int i, HashMap list) {
        List keys = new ArrayList(list.keySet()); 
        String result = null;  
        int count = 1;
        
        for(Object word : keys){
            if(count==i){
                result = (String) word;
                break;
            }
            count++;
        } 
        return result;
    }
}
