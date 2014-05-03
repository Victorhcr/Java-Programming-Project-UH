package com.victor.nytwords.gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class Chart {

    private String word;
    private int beg;
    private int end;
    private String[] args;
    private FileReader fileNorm;
    private FileReader filePred;

    public Chart() throws FileNotFoundException {
        FileReader main = new FileReader("files/main.txt");
        getData(main);

        this.fileNorm = new FileReader("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        this.filePred = new FileReader("files/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");

        try {

            XYSeries series1 = new XYSeries("Word: " + this.word);
            addData(series1, this.fileNorm);

            XYSeries series2 = new XYSeries("Prediction");
            addData(series2, this.filePred);

            XYSeriesCollection xyDataset = new XYSeriesCollection();
            xyDataset.addSeries(series1);
            xyDataset.addSeries(series2);

            JFreeChart chart = ChartFactory.createXYLineChart("Article Hits Chart", "Years", "Percentage of Articles", xyDataset, PlotOrientation.VERTICAL, true, false, false);
            chart.setBackgroundPaint(Color.yellow);

            XYPlot plot = (XYPlot) chart.getPlot();
            setPlot(plot);

            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);

            ChartFrame frame = new ChartFrame("New York Times Articles", chart);
            frame.setSize(450, 250);
            frame.setVisible(true);

        } catch (Exception i) {
            System.out.println(i);
        }
    }

    
    /**
     * This method sets the plot characteristics
     * @param plot 
     */
    public void setPlot(XYPlot plot) {
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.orange);
        plot.setAxisOffset(new RectangleInsets(50, 0, 20, 5));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
    }

    /**
     * This method gets data from file in user computer and check which file to
     * parse
     *
     * @param main
     */
    public void getData(FileReader main) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(main);

            this.word = br.readLine();
            this.beg = Integer.parseInt(br.readLine());
            this.end = Integer.parseInt(br.readLine());

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

    /**
     * This method adds the data parsed from chosen file in getData method and
     * adds to the chart
     *
     * @param series
     * @param file
     */
    public void addData(XYSeries series, FileReader file) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(file);

            while (!(sCurrentLine = br.readLine()).equals("-")) {
                parts = sCurrentLine.split(": ");
                series.add(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            }
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

    /**
     * Returns word to be plotted
     *
     * @return
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Returns first year of the parsing
     *
     * @return
     */
    public int getBeg() {
        return this.beg;
    }

    /**
     * Returns last year of the parsing
     *
     * @return
     */
    public int getEnd() {
        return this.end;
    }
}
