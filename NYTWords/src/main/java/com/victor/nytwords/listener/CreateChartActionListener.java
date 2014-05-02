/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.listener;

import com.victor.nytwords.gui.View;
import com.victor.nytwords.gui.Chart;
import com.victor.nytwords.functions.YearHits;
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

    public CreateChartActionListener(JTextField input, JFrame guiFrame, JComboBox firstYears, JComboBox lastYears) {
        this.input = input;
        this.guiFrame = guiFrame;
        this.firstYears = firstYears;
        this.lastYears = lastYears;
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
        System.out.println(word);
        if (lastYears.getSelectedItem()==null) return;
        int beg = Integer.parseInt(String.valueOf(firstYears.getSelectedItem()));
        int end = Integer.parseInt(String.valueOf(lastYears.getSelectedItem()));
        if (word.isEmpty()) return;
        YearHits yh = new YearHits(word, beg, end);
        try {
            yh.start();
            guiFrame.dispose();
            Chart chart = new Chart();
            chart.startP();
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String replace(String word){
        word = word.replace("/", "");
        word = word.replace("\\", "");
        word = word.replace("<", "");
        word = word.replace(">", "");
        word = word.replace("{", "");
        word = word.replace("}", "");
        return word;
    }
}
