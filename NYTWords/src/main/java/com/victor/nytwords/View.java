package com.victor.nytwords;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import com.victor.nytwords.functions.Chart;
import com.victor.nytwords.functions.YearHits;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Create GUI where user can write word to be parsed and
 * a range of years to plot
 * @author Victor Rodrigues
 */
public class View implements ActionListener {

    JTextField input;
    JFrame guiFrame;
    private final JComboBox firstYears;
    private final JComboBox lastYears = new JComboBox();

    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    
    /**
     * Initialize the class
     * @param args 
     */
    public static void main(String[] args) {
        new View();
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

    /**
     * Set the GUI configurations
     */
    public View() {
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("New York Times Articles");
        guiFrame.setSize(350, 150);

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
        firstYears.addActionListener(this);

        //The third JPanel contains a JLabel and JCombobox
        final JPanel combo2Panel = new JPanel();
        JLabel combo2Lbl = new JLabel("Last Year:");

        comboPanel.add(combo2Lbl);
        comboPanel.add(lastYears);

        JButton createChart = new JButton("Create Chart!");
        
        //Add action listener to button
        createChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                String word = input.getText();
                int beg = Integer.parseInt(String.valueOf(firstYears.getSelectedItem()));
                int end = Integer.parseInt(String.valueOf(lastYears.getSelectedItem()));
                
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
        }); 

        //The JFrame uses the BorderLayout layout manager.
        //Put the two JPanels and JButton in different areas.
        guiFrame.add(textPanel, BorderLayout.NORTH);
        guiFrame.add(comboPanel, BorderLayout.CENTER);
        guiFrame.add(combo2Panel, BorderLayout.SOUTH);
        guiFrame.add(createChart, BorderLayout.SOUTH);

        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }

}
