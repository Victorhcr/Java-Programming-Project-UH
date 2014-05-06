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

    public RelatedWords(String input) throws FileNotFoundException {
        this.word = input;
        this.numberDocuments = getNumberDocuments(new FileReader("files/history/main.txt"));
    }

    public HashMap getRelatedWords() {
        HashMap result = new HashMap<String, Integer>();
        HashMap parsedWords = new HashMap<String, Integer>();
        for (int i = 1; i < this.numberDocuments; i++) {
            parsedWords = getData(i);
            if (parsedWords.containsKey(this.word)) {
                parsedWords.remove(this.word);
                result = updateRelatedWords(parsedWords,result);
            }
        }
        result = sortHashMapByValues(result);
        return result;
    }

    public HashMap updateRelatedWords(HashMap<String, Integer> parsedWords,
            HashMap<String, Integer> result) {
        for (Object wordParsed : parsedWords.keySet()) {
            if (result.containsKey(wordParsed) && parsedWords.get(wordParsed) > result.get(wordParsed)) {
                if (parsedWords.get(wordParsed) > result.get(wordParsed)) {
                    result.put((String) wordParsed, parsedWords.get(wordParsed));
                }
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
     * @param main
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
     * This method adds the data parsed from chosen file in getData method and
     * adds to the chart
     *
     * @param series
     * @param file
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
