/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.filehandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class RelatedWords {

    private int numberDocuments;
    private String word;

    /**
     * Constructor of RelatedWords class
     * @param input Word written by the user
     * @param file Path in the computer to the history folder
     * @throws java.io.FileNotFoundException File is not found
     */
    public RelatedWords(String input, String file) throws FileNotFoundException {
        this.word = input;
        this.numberDocuments = getNumberDocuments(new FileReader(file));
    }

    /**
     * Gets all words from file if the word written is in the file
     * @return HashMap with related words as key and the number of times
     * they were input in the program as value
     */
    public HashMap getRelatedWords() {
        HashMap result = new HashMap<String, Integer>();
        HashMap parsedWords = new HashMap<String, Integer>();
        for (int i = 1; i < this.numberDocuments; i++) {
            parsedWords = getData(i);
            if (parsedWords.containsKey(this.word)) {
                parsedWords.remove(this.word);
                result = updateRelatedWords(parsedWords, result);
            }
        }
        result = sortHashMapByValues(result);
        return result;
    }

    /**
     * This method updates the quantity of words parsed in log files that
     * have the word written by the user
     * @param parsedWords Words parsed in a new log file
     * @param result Words parsed until the moment
     * @return Words parsed in a new log file union with words parsed until the moment
     */
    public HashMap updateRelatedWords(HashMap<String, Integer> parsedWords,
            HashMap<String, Integer> result) {
        for (Object wordParsed : parsedWords.keySet()) {
            if (result.containsKey(wordParsed)) {
                result.put((String) wordParsed, parsedWords.get(wordParsed) + result.get(wordParsed));
            } else {
                result.put((String) wordParsed, parsedWords.get(wordParsed));
            }
        }
        return result;
    }

    /**
     * This method gets data from file in user computer and check which file to
     * parse
     *
     * @param main Main file of History folder
     */
    public int getNumberDocuments(FileReader main) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(main);

            this.numberDocuments = Integer.parseInt(br.readLine());
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
        return this.numberDocuments;
    }

    /**
     * Parse log file in the history folder
     * @param logTime The time the program was used
     * @return HashMap with words as key and the number of times
     * they were input in the program as value 
     */
    public HashMap getData(int logTime) {
        HashMap list = new HashMap<String, Integer>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(new FileReader("files/history/log" + logTime + ".txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                parts = sCurrentLine.split(": ");
                list.put(parts[0], Integer.parseInt(parts[1]));
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
        return list;
    }

    /**
     * Sort the passedMap by value
     * @param passedMap A HashMap of each history log file
     * @return Sort HashMap by value
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
}
