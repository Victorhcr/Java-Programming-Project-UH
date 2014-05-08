package com.victor.nytwords.gui;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import com.victor.nytwords.filehandler.History;
import com.victor.nytwords.filehandler.MainFileWordsPerLog;
import com.victor.nytwords.listener.CreateChartActionListener;
import com.victor.nytwords.listener.YearsActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Create GUI where user can write word to be parsed and a range of years to
 * plot
 *
 * @author Victor Rodrigues
 */
public class View {

    JTextField input;
    JFrame guiFrame = new JFrame();
    JPanel panelTop = new JPanel();
    JPanel panelBottom = new JPanel(new GridLayout(1, 0));
    JPanel words = new JPanel();
    private final JComboBox firstYears;
    private final JComboBox lastYears = new JComboBox();
    JLabel word1 = new JLabel("Search for something!");
    JLabel word2 = new JLabel("");
    JLabel word3 = new JLabel("");
    private MainFileWordsPerLog mlw = new MainFileWordsPerLog("files/history/main.txt");
    private final int logTime = getLogTime();

    /**
     * Set the GUI configurations
     */
    public View(String file) {      
        this.mlw = new MainFileWordsPerLog(file);
        //Options for the First Year
        String[] firstYearOptions = new String[2013 - 1900 + 1];
        int count = 0;
        for (int i = 1900; i < 2014; i++) {
            firstYearOptions[count] = i + "";
            count++;
        }

        final JPanel textPanel = new JPanel();
        input = new JTextField(10);
        input.setBounds(100, 20, 100, 20);
        textPanel.add(new JLabel("Type word:"));
        textPanel.add(input);

        //The first JPanel contains two sets of JLabel and JCombobox
        final JPanel comboPanel = new JPanel();
        firstYears = new JComboBox(firstYearOptions);
        comboPanel.add(new JLabel("First Year:"));
        comboPanel.add(firstYears);
        comboPanel.add(new JLabel("Last Year:"));
        comboPanel.add(lastYears);

        ActionListener listenerYear = new YearsActionListener(firstYears, lastYears);
        firstYears.addActionListener(listenerYear);

        JButton createChart = new JButton("Create Chart!");

        createChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final ActionListener listenerButton = new CreateChartActionListener(input,
                guiFrame, firstYears, lastYears, logTime);
                listenerButton.actionPerformed(e);
                updateMostSearchedWords("files/all_words_statistics/notes.txt");
            }
        });

        updateMostSearchedWords("files/all_words_statistics/notes.txt");
        this.words = addMostSearchedWords(this.word1,this.word2,this.word3);
        
        this.panelTop = addToPanelTop(textPanel, comboPanel, createChart);

        panelBottom.add(words, BorderLayout.CENTER);

        this.guiFrame = setGuiFrame(panelTop, panelBottom);
    }
    
    public JPanel addToPanelTop(JPanel textPanel, JPanel comboPanel, 
            JButton createChart){
        JPanel panelTop = new JPanel();
        //The JFrame uses the BorderLayout layout manager.
        //Put the two JPanels and JButton in different areas.
        panelTop.add(textPanel, BorderLayout.NORTH);
        panelTop.add(comboPanel, BorderLayout.CENTER);
        panelTop.add(createChart, BorderLayout.SOUTH);
        return panelTop;
    }

    public JPanel addMostSearchedWords(JLabel word1, JLabel word2, JLabel word3) {
        JPanel words = new JPanel();
        JLabel textWords = new JLabel("Most Searched Words:");
        words.add(textWords);
        words.add(word1);
        words.add(word2);
        words.add(word3);
        return words;
    }


    public JFrame setGuiFrame(JPanel panelTop, JPanel panelBottom) {
        JFrame guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("New York Times Articles");
        guiFrame.setSize(800, 100);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        guiFrame.add(panelTop, BorderLayout.NORTH);
        guiFrame.add(panelBottom, BorderLayout.SOUTH);
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
        
        return guiFrame;
    }

    public int getLogTime() {
        try {
            mlw.start();
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mlw.getLog();
    }

    public void updateMostSearchedWords(String file) {
        History wf = new History(file);
        if (wf.getIndexOrdered(1) != null) {
            this.word1.setText("1. " + wf.getIndexOrdered(1));
        }

        if (wf.getIndexOrdered(2) != null) {
            this.word2.setText("2. " + wf.getIndexOrdered(2));
        }

        if (wf.getIndexOrdered(3) != null) {
            this.word3.setText("3. " + wf.getIndexOrdered(3));
        }
    }

}
