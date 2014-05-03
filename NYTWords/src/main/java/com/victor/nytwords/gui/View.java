package com.victor.nytwords.gui;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import com.victor.nytwords.listener.CreateChartActionListener;
import com.victor.nytwords.listener.YearsActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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
    private final JComboBox firstYears;
    private final JComboBox lastYears = new JComboBox();

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
        ActionListener listenerYear = new YearsActionListener(firstYears, lastYears);
        firstYears.addActionListener(listenerYear);

        //The third JPanel contains a JLabel and JCombobox
        final JPanel combo2Panel = new JPanel();
        JLabel combo2Lbl = new JLabel("Last Year:");

        comboPanel.add(combo2Lbl);
        comboPanel.add(lastYears);

        JButton createChart = new JButton("Create Chart!");

        ActionListener listenerButton = new CreateChartActionListener(input, guiFrame, firstYears, lastYears);
        createChart.addActionListener(listenerButton);

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
