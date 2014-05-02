/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.listener;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Victorhcr
 */
public class YearsActionListener implements java.awt.event.ActionListener{
    
    private JComboBox firstYears;
    private JComboBox lastYears;
    
    public YearsActionListener(JComboBox firstYears, JComboBox lastYears) {
        this.firstYears = firstYears;
        this.lastYears = lastYears;
    }
    
    /**
     * Updates the range of last year to be chosen 
     * when the first year is chosen
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int fYS = Integer.parseInt(String.valueOf(firstYears.getSelectedItem()));
        String[] lastYearOptions;
        int max;
        if (fYS + 30 < 2013) {
            lastYearOptions = new String[25];
            max = fYS + 25 - 1;
        } else {
            lastYearOptions = new String[2013 - fYS + 1];
            max = 2013;
        }
        //Options for the Last Year
        int count = 0;
        for (int j = fYS; j < max + 1; j++) {
            lastYearOptions[count] = j + "";
            count++;
        }
        
        lastYears.setModel(new DefaultComboBoxModel(
            lastYearOptions));
        lastYears.setSelectedIndex(count-1);
    }
    
}
