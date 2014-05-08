
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.listener;

import com.victor.nytwords.filehandler.History;
import com.victor.nytwords.filehandler.WordsPerLog;
import com.victor.nytwords.functions.YearHits;
import com.victor.nytwords.gui.Chart;
import com.victor.nytwords.gui.View;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Victorhcr
 */
public class CreateChartActionListener implements java.awt.event.ActionListener {

    JTextField input;
    JFrame guiFrame;
    private final JComboBox firstYears;
    private final JComboBox lastYears;
    private final int logTime;
    private final WordsPerLog logFile;

    public CreateChartActionListener(JTextField input, JFrame guiFrame,
            JComboBox firstYears, JComboBox lastYears, int logTime) {
        this.logTime = logTime;
        this.input = input;
        this.guiFrame = guiFrame;
        this.firstYears = firstYears;
        this.lastYears = lastYears;
        this.logFile = new WordsPerLog(logTime,"files/history/");
    }
    
    /**
     * Create the chart based on the user's inputs
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Execute when button is pressed
        String word = input.getText();
        word = replace(word);
        if (lastYears.getSelectedItem()==null) return;
        int beg = Integer.parseInt(String.valueOf(firstYears.getSelectedItem()));
        int end = Integer.parseInt(String.valueOf(lastYears.getSelectedItem()));
        if (word.isEmpty()) return;
        YearHits yh = new YearHits(word, beg, end);
        History wf = new History("files/all_words_statistics/notes.txt");
        try {
            this.logFile.updateWordsStatisticsFile(word);
            wf.updateWordsStatisticsFile(word);
            yh.start();
            Chart chart = new Chart();
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Avoid errors in the program by replacing characters in the user's input
     * @param word
     * @return 
     */
    public String replace(String word){
        String all = " abcdefghijklmnopqrstuvxwyz1234567890";
        String result = "";
        word = word.trim();
        char[] wordArray = word.toCharArray();
        for(int i = 0; i < word.length();i++){
            if(all.contains(Character.toString(word.toLowerCase().charAt(i)))){
                result+=Character.toString(word.charAt(i));
            }
        }
        return result;
    }
}
