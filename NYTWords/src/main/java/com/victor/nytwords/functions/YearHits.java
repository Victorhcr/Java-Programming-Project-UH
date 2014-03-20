/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.functions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Victorhcr
 */
public class YearHits {

    private HashMap yearHits;
    private String word;
    private int beg;
    private int end;
    
    public YearHits(){
        this.yearHits = new HashMap<>();
    }
    
    public void start() throws IOException{
        ask();
        
        try{
            this.yearHits = addHits(this.word,this.beg,this.end);
        } catch (Exception e) {
            System.out.println("Problem in adding hits for word. More: \n" + e);
        }
        
        writeFile();
    }
    
    private void ask(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Type a word: ");
        this.word = reader.nextLine();
        
        System.out.println("Type beginning year: ");
        this.beg = Integer.parseInt(reader.nextLine());
        
        System.out.println("Type final year: ");
        this.end = Integer.parseInt(reader.nextLine());
    }
    
    /* Add number of articles for the word chosen 
     * in each year in the interval beg-end */
    public HashMap addHits(String word, int beg,int end) throws Exception{
        HashMap<Integer,Integer> result = new HashMap();
        String json;
        word = java.net.URLEncoder.encode(word, "UTF-8");
        if(checkEntriesBound(word, beg, end)){
            word = "\"\"";
            end = 2013;
            beg = 2013;
        }
        int temp = beg;
        
        while(temp <= end){
            json = readUrl("http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&q=" + word + "&fq=International&begin_date=" + temp + "0101&end_date=" + temp + "1231&sort=newest&fl=web_url&facet_filter=true&api-"
                + "key=4ddea52e9d7cc30124e3efe576c26530:14:68745688");
            result.put(temp, parse(json));
            temp++;
        }
        return result;
    }
    
    private boolean checkEntriesBound(String word, int beg, int end){
        if(word.equals("") || beg > end || end > 2013){
            return true;
        }
        return false;
    }
    
    
    
    
    private String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    
    private int parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("response");
        JsonObject jobject2 = jobject.getAsJsonObject("meta");      
        
        return jobject2.get("hits").getAsInt();
    }
    
    private void writeFile() throws IOException{
        FileWriter file = new FileWriter("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        
        List sortedKeys = new ArrayList(this.yearHits.keySet());
        Collections.sort(sortedKeys);
        
        for(Object year : sortedKeys){
            file.append(year.toString() + ": " + this.yearHits.get(year) + "\n");
        }
        
        file.close();
    }
    
    public String getWord(){
        return this.word;
    }
    
    public int getBeg(){
        return this.beg;
    }
    
    public int getEnd(){
        return this.end;
    }
    
    public HashMap getMap(){
        return this.yearHits;
    }
}
