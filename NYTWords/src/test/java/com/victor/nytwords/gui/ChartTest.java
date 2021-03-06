/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Victorhcr
 */
public class ChartTest {
    private Chart c;
    private String word;
    private String beg;
    private String end;

    public ChartTest() throws FileNotFoundException, IOException{
        this.c = new Chart();
        getFile();
    }
    
    public void getFile(){
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("testfiles/words_data/main.txt"));
            
            this.word = br.readLine();
            this.beg = br.readLine();
            this.end = br.readLine();

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
    }
    
    public void writeFile(String word, String beg, String end) throws IOException{
        FileWriter write = new FileWriter("testfiles/words_data/main.txt");
        write.append(word + "\n");
        write.append(beg + "\n");
        write.append(end + "\n");
        write.close();
    }
    
    /**
     * Test of getWord method, of class Chart.
     */
    @Test
    public void getWord() throws IOException {
        FileReader main = null;
        try {
            main = new FileReader("testfiles/words_data/main.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChartTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.c.getData(main);
        
        assertEquals("war", this.c.getWord());
    }
    
    /**
     * Test of getBeg method, of class Chart.
     */
    @Test
    public void getBeg() {
        FileReader main = null;
        try {
            main = new FileReader("testfiles/words_data/main.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChartTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.c.getData(main);
        
        assertEquals(1980, this.c.getBeg());
    }
    
    /**
     * Test of getEnd method, of class Chart.
     */
    @Test
    public void getEnd() {
        FileReader main = null;
        try {
            main = new FileReader("testfiles/words_data/main.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChartTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.c.getData(main);
        
        assertEquals(2013, this.c.getEnd());
    }

}
