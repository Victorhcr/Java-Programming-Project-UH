/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.filehandler;

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
public class AllWordsFile {

    private HashMap list;
    private String file;

    public AllWordsFile(String input) {
        this.file = input;
        this.list = new HashMap<String, Integer>();
        this.list = getData();
        this.list = sortHashMapByValues(this.list);
    }

    public void updateWordsStatisticsFile(String word) throws IOException {
        this.list = getData();
        if (!this.list.containsKey(word)) {
            addNewWord(word);
        } else {
            replaceSelected(word, (Integer) this.list.get(word));
        }
        this.list = sortHashMapByValues(this.list);
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

    public void addNewWord(String word) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter("files/all_words_statistics/notes.txt", true));
        output.append(word + ": 1" + "\n");
        output.close();
    }

    public void replaceSelected(String replaceWith, Integer type) {
        try {
            // input the file content to the String "input"
            BufferedReader file = new BufferedReader(new FileReader(this.file));
            String line;
            String input = "";

            while ((line = file.readLine()) != null) {
                input += line + '\n';
            }

            // this if structure determines whether or not to replace
            input = input.replace(replaceWith + ": " + type, replaceWith + ": " + (type + 1));

            // write the new String with the replaced line OVER the same file
            FileOutputStream File = new FileOutputStream(this.file);
            File.write(input.getBytes());

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    /**
     * This method adds the data parsed from chosen file in getData method and
     * adds to the chart
     *
     * @param series
     * @param file
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

    public String getIndexOrdered(int i) {
        List keys = new ArrayList(this.list.keySet()); 
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
