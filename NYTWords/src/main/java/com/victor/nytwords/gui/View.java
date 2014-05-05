package com.victor.nytwords.gui;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import com.victor.nytwords.filehandler.AllWordsFile;
import com.victor.nytwords.filehandler.MainLogWordsFile;
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
    JFrame guiFrame;
    JPanel panelTop;
    JPanel panelBottom;
    JFrame guiBottomFrame;
    private final JComboBox firstYears;
    private final JComboBox lastYears = new JComboBox();
    JLabel word1 = new JLabel("Search for something!");
    JLabel word2 = new JLabel("");
    JLabel word3 = new JLabel("");
    private final MainLogWordsFile mlw = new MainLogWordsFile();
    private final int logTime;

    /**
     * Set the GUI configurations
     */
    public View() {
        this.logTime = getLogTime();
        guiFrame = new JFrame();
        guiBottomFrame = new JFrame();
        panelTop = new JPanel();
        panelBottom = new JPanel(new GridLayout(1,0));

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("New York Times Articles");
        guiFrame.setSize(800, 100);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        //Options for the First Year
        String[] firstYearOptions = new String[2013 - 1900 + 1];
        int count = 0;
        for (int i = 1900; i < 2014; i++) {
            firstYearOptions[count] = i + "";
            count++;
        }

        final JPanel textPanel = new JPanel();
        JLabel textLbl = new JLabel("Type word:");
        input = new JTextField(10);
        input.setBounds(100, 20, 100, 20);
        textPanel.add(textLbl);
        textPanel.add(input);

        //The first JPanel contains a JLabel and JCombobox
        final JPanel comboPanel = new JPanel();
        JLabel comboLbl = new JLabel("First Year:");
        firstYears = new JComboBox(firstYearOptions);

        String firstYearSelected = String.valueOf(firstYears.getSelectedItem());

        comboPanel.add(comboLbl);
        comboPanel.add(firstYears);
        ActionListener listenerYear = new YearsActionListener(firstYears, lastYears);
        firstYears.addActionListener(listenerYear);

        //The third JPanel contains a JLabel and JCombobox
        final JPanel combo2Panel = new JPanel();
        JLabel combo2Lbl = new JLabel("Last Year:");

        comboPanel.add(combo2Lbl);
        comboPanel.add(lastYears);

        JButton createChart = new JButton("Create Chart!");

        final ActionListener listenerButton = new CreateChartActionListener(input,
                guiFrame, firstYears, lastYears, logTime);

        createChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listenerButton.actionPerformed(e);
                update();
            }
        });
        
        update();

        //The JFrame uses the BorderLayout layout manager.
        //Put the two JPanels and JButton in different areas.
        panelTop.add(textPanel, BorderLayout.NORTH);
        panelTop.add(comboPanel, BorderLayout.CENTER);
        panelTop.add(combo2Panel, BorderLayout.SOUTH);
        panelTop.add(createChart, BorderLayout.SOUTH);
        
        final JPanel words = new JPanel();
        JLabel textWords = new JLabel("Most Searched Words:");
        words.add(textWords);
        words.add(this.word1);
        words.add(this.word2);
        words.add(this.word3);
        
        panelBottom.add(words, BorderLayout.CENTER);

        guiFrame.add(panelTop, BorderLayout.NORTH);
        guiFrame.add(panelBottom, BorderLayout.SOUTH);
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }
    
    public int getLogTime(){
        try {
            mlw.start();
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mlw.getLog();
    }

    public void update() {
        AllWordsFile wf = new AllWordsFile();
        if(wf.getIndexOrdered(1) != null){
            this.word1.setText("1. " + wf.getIndexOrdered(1));
        }
        
        if(wf.getIndexOrdered(2) != null){
            this.word2.setText("2. " + wf.getIndexOrdered(2));
        }
        
        if(wf.getIndexOrdered(3) != null){
            this.word3.setText("3. " + wf.getIndexOrdered(3));
        }
    }

}