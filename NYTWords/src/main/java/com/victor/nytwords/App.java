package com.victor.nytwords;

import com.victor.nytwords.functions.Chart;
import com.victor.nytwords.functions.YearHits;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

    

public class App
{
    
    public static void main( String[] args ) throws IOException 
    {
        YearHits hits = new YearHits();
        hits.start();
    }

    
    
}
