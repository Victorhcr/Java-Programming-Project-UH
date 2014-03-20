/*
 * This class is still on construction. Please do not evaluate this.
 */

package com.victor.nytwords.functions;

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
 *
 * @author Victorhcr
 */
public class Chart extends Application {
    private String word;
    private int beg;
    private int end;
    private FileReader file;
    private Stage stage;
    
    
    public Chart(String word, int beg, int end){
        this.word = word;
        this.beg = beg;
        this.end = end;
        System.out.println("1");
    }
    
    public void laun(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {    
        
        this.file = new FileReader("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        
        System.out.println("2");
        
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
        series1.setName("Word: " + this.word);

        addData(series1);

        Scene scene = new Scene(lineChart);
        lineChart.getData().addAll(series1);
        //scene.getStylesheets().add("linechartsample/Chart.css");                      
        stage.setScene(scene);
        stage.show();
    }

    public void addData(XYChart.Series series) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(this.file);

            while ((sCurrentLine = br.readLine()) != null) {
                parts = sCurrentLine.split(": ");
                series.getData().add(new XYChart.Data(parts[0], Integer.parseInt(parts[1])));
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
        
        System.out.println("3");
    }
}
