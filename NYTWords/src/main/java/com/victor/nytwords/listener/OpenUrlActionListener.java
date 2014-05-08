/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.listener;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;

/**
 *
 * @author Victorhcr
 */
public class OpenUrlActionListener implements java.awt.event.ActionListener {

    private String link;

    /**
     * Constructor of OpenUrlActionListener class
     * @param input URL String to article site
     * @throws MalformedURLException 
     */
    public OpenUrlActionListener(String input) throws MalformedURLException {
        this.link = input;
    }

    /**
     * Opens web page
     * @param e Action done in Chart GUI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        openWebpage(this.link);
    }

    /**
     * Transforms URL to URI and opens Desktop
     * @param urlString URL of article page
     */
    public static void openWebpage(String urlString) {
        try {  
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(urlString));       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
