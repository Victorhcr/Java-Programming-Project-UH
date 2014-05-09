/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.functions;

import com.victor.nytwords.filehandler.SaveAPIData;
import com.victor.nytwords.abstractclasses.GetAPIData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.HashMap;

/**
 * Gets Data from New York Times API
 * and saves in the computer
 * @author Victor Rodrigues
 */
public class YearHits extends GetAPIData {

    private HashMap yearHits;
    private String word;
    private int beg = -1;
    private int end;
    private SaveAPIData handle;
    
    public YearHits(){
        this.yearHits = new HashMap<>();
    }
    
    /**
     * Constructor of YearHits class
     * @param word Word written by the user
     * @param beg First year chosen by user
     * @param end Last year chosen by user
     */
    public YearHits(String word, int beg, int end){
        this.yearHits = new HashMap<>();
        this.word = word;
        this.beg = beg;
        this.end = end;
    }
    
    /**
     * Start program with variables from View class
     * @throws IOException 
     */
    public void start() throws IOException{
        try{
            this.yearHits = addHits(this.word,this.beg,this.end);
        } catch (Exception e) {
            System.out.println("Problem in adding hits for word. More: \n" + e);
        } 
        this.handle = new SaveAPIData(this.word,this.beg,this.end,this.yearHits);
        
        writeFile();
        writeFilePred();
        writeFileMain();
    }
    
    /**
     * Add number of articles for the word chosen 
     * in each year in the interval beg-end 
     * @param word Word written by the user
     * @param beg First year chosen by user
     * @param end Last year chosen by user
     * @return Return HashMap with key being the year and value being 
     * the percentage of articles with the word chosen in that year
     * @throws java.lang.Exception
     **/
    public HashMap addHits(String word, int beg,int end) throws Exception{
        HashMap<Double,Double> result = new HashMap();
        String jsonWord;
        String jsonTotal;
        word = java.net.URLEncoder.encode(word, "UTF-8");
        if(checkEntriesBound(word, beg, end)){
            word = "\"\"";
            end = 2013;
            beg = 2013;
        }
        int temp = beg;
        
        while(temp <= end){
            jsonWord = readUrl("http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&q=" + word + "&fq=International&begin_date=" + temp + "0101&end_date=" + temp + "1231&sort=newest&fl=web_url&facet_filter=true&api-"
                + "key=4ddea52e9d7cc30124e3efe576c26530:14:68745688");
            jsonTotal = readUrl("http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&fq=International&begin_date=" + temp + "0101&end_date=" + temp + "1231&sort=newest&fl=web_url&facet_filter=true&api-"
                + "key=4ddea52e9d7cc30124e3efe576c26530:14:68745688");
            
            result.put((double) temp, parse(jsonWord)*100/parse(jsonTotal));
            temp++;
        }
        return result;
    }
    
    /**
     * Check if entries are valid for the parsing
     * @param word Word written by the user
     * @param beg First year chosen by user
     * @param end Last year chosen by user
     * @return Check if year and word chosen are in the boundaries
     */
    private boolean checkEntriesBound(String word, int beg, int end){
        if(word.equals("") || beg > end || end > 2013){
            return true;
        }
        return false;
    }
    
    /**
     * Parse page received and get number 
     * of articles with that word
     * @param jsonLine JSon String from API
     * @return return number of articles in that year
     */
    private double parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("response");
        JsonObject jobject2 = jobject.getAsJsonObject("meta");      
        
        return jobject2.get("hits").getAsDouble();
    }
    
    /**
     * Write file specifying the word,
     * the first year and last year of
     * last time (now) program was executed
     * @throws IOException 
     */
    private void writeFileMain() throws IOException{
        this.handle.writeFileMain();
    }
    
    /**
     * Write file with all data normalized (in percentage per year)
     * @throws IOException 
     */
    private void writeFile() throws IOException{  
        this.handle.writeFile();
    }
    
    /**
     * Predict the next year Article hits
     * @throws IOException 
     */
    private void writeFilePred() throws IOException{
        this.handle.writeFilePred();
    }
    
    /**
     * Return word parsed
     * @return Word parsed
     */
    public String getWord(){
        return this.word;
    }
    
    /**
     * Return first year parsed 
     * @return First year
     */
    public int getBeg(){
        return this.beg;
    }
    
    /**
     * Return last year parsed 
     * @return Last Year
     */
    public int getEnd(){
        return this.end;
    }
    
    /**
     * Return HashMap
     * @return HashMap of year => percentage of articles
     */
    public HashMap getMap(){
        return this.yearHits;
    }
}
