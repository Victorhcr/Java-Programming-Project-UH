/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.abstractclasses;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Victorhcr
 */
public abstract class GetAPIData {
    
    /**
     * Read API URL Page
     * @param urlString URL of NYTimes API
     * @return Return data from site as a string
     * @throws Exception 
     */
    public String readUrl(String urlString) throws Exception {
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
}
