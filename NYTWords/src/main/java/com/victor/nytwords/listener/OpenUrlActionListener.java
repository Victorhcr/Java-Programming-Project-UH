/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victor.nytwords.listener;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author Victorhcr
 */
public class OpenUrlActionListener implements java.awt.event.ActionListener {

    private String link;

    public OpenUrlActionListener(String input) throws MalformedURLException {
        this.link = input;
    }

    /**
     * Create the chart based on the user's inputs
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        openWebpage(this.link);
    }

    public static void openWebpage(String urlString) {
        try {  
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(urlString));       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
