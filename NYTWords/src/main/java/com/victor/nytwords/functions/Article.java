/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.functions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.victor.nytwords.filehandler.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Gets Data from New York Times API and saves in the computer
 *
 * @author Victor Rodrigues
 */
public class Article {

    private String[] article;
    private String word;
    private int beg = -1;
    private int end;
    private Data handle;

    /**
     * 
     */
    public Article() {
        this.article = new String[3];
    }

    /**
     * Constructor of Article class
     * @param word Word written by user
     * @param beg First year parsed
     * @param end Last year parsed
     */
    public Article(String word, int beg, int end) {
        this.article = new String[3];
        this.word = word;
        this.beg = beg;
        this.end = end;
    }

    /**
     * Start program with variables from View class
     *
     * @throws IOException
     */
    public void start() throws IOException {
        try {
            this.article = addHits(this.word, this.beg, this.end);
        } catch (Exception e) {
            System.out.println("Problem in getting word data (headline,url,snippet). More: \n" + e);
        }
    }

    /* Add number of articles for the word chosen 
     * in each year in the interval beg-end */
    public String[] addHits(String word, int beg, int end) throws Exception {
        String[] result = new String[3];
        String jsonWord;
        String jsonTotal;
        word = java.net.URLEncoder.encode(word, "UTF-8");
        if (checkEntriesBound(word, beg, end)) {
            word = "\"\"";
            end = 2013;
            beg = 2013;
        }

        jsonWord = readUrl("http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&q=" + word + "&fq=International&begin_date=" + beg + "0101&end_date=" + end + "1231&sort=newest&fl=headline,web_url,snippet&facet_filter=true&api-"
                + "key=4ddea52e9d7cc30124e3efe576c26530:14:68745688");

        result = parse(jsonWord);
        System.out.println(result[0]);

        return result;
    }

    /**
     * Check if entries are valid for the parsing
     *
     * @param word
     * @param beg
     * @param end
     * @return
     */
    private boolean checkEntriesBound(String word, int beg, int end) {
        if (word.equals("") || beg > end || end > 2013) {
            return true;
        }
        return false;
    }

    /**
     * Read API URL Page
     *
     * @param urlString
     * @return
     * @throws Exception
     */
    private String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Parse page received and get number of articles with that word
     *
     * @param jsonLine String from web site
     * @return Headline, Snippet and a Link of most recent Article
     * in the parsing
     */
    private String[] parse(String jsonLine) {
        String[] result = new String[3];
        JsonObject headline;
        
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("response");
        JsonArray jarray = jobject.getAsJsonArray("docs");
        if(jarray.size()>0){
            jobject = jarray.get(0).getAsJsonObject();
            
            headline = jobject.getAsJsonObject("headline");
            result[0] = headline.get("main").toString();
            result[1] =  jobject.get("snippet").toString();
            result[2] = jobject.get("web_url").toString().replace("\"", "");
        }
        return result;
    }

    /**
     * Return word parsed
     *
     * @return Word parsed
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Return first year parsed
     *
     * @return First year
     */
    public int getBeg() {
        return this.beg;
    }

    /**
     * Return last year parsed
     *
     * @return Last Year
     */
    public int getEnd() {
        return this.end;
    }

    /**
     * Return HashMap
     *
     * @return Headline, Snippet and a Link of most recent Article
     * in the parsing
     */
    public String[] getData() {
        return this.article;
    }
}
