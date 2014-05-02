/*
 * This class is still on construction. Please do not evaluate this.
 */
package com.victor.nytwords.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * This class will plot the data we have
 * @author Victor Rodrigues
 */
public class Chart extends Application {

    private String word;
    private int beg;
    private int end;
    private String[] args;
    private FileReader fileNorm;
    private FileReader filePred;
    private Stage stage;
    
    public Chart() {

    }
    
    /**
     * This method starts the program
     * @throws InterruptedException 
     */
    public void startP() throws InterruptedException{
        main(args);
    }
    
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    /**
     * This method process the data and transform it into the wanted chart
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        FileReader main = new FileReader("files/main.txt");
        getData(main);
        
        this.fileNorm = new FileReader("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        this.filePred = new FileReader("files/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");

        stage.setTitle("Article Hits Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("New York Times Articles");
        lineChart.setCreateSymbols(false);
        lineChart.setAlternativeRowFillVisible(false);
        
        XYChart.Series series1 = new XYChart.Series();
        setSeries(series1, "Word: " + this.word, this.fileNorm);

        XYChart.Series series2 = new XYChart.Series();
        setSeries(series2, "Prediction", this.filePred);
        
        Scene scene = new Scene(lineChart);
        lineChart.getData().addAll(series1,series2);
        //scene.getStylesheets().add("linechartsample/Chart.css");                      
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method adds the data to the series parameter
     * @param series
     * @param name
     * @param file 
     */
    public void setSeries(XYChart.Series series, String name, FileReader file){
        series.setName(name);
        addData(series,file);
    }
    
    /**
     * This method gets data from file in user computer 
     * and check which file to parse
     * @param main 
     */
    public void getData(FileReader main){
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
     * This method adds the data parsed from chosen file in getData
     * method and adds to the chart
     * @param series
     * @param file 
     */
    public void addData(XYChart.Series series,FileReader file) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(file);

            while (!(sCurrentLine = br.readLine()).equals("-")) {
                parts = sCurrentLine.split(": ");
                series.getData().add(new XYChart.Data(parts[0], Double.parseDouble(parts[1])));
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
     * @return 
     */
    public String getWord(){
            return this.word;
    }
    
    /**
     * Returns first year of the parsing
     * @return 
     */
    public int getBeg(){
        return this.beg;
    }
    
    /**
     * Returns last year of the parsing
     * @return 
     */
    public int getEnd(){
        return this.end;
    }
}
