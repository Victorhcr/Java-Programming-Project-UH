package com.victor.nytwords;

import com.victor.nytwords.functions.Chart;
import com.victor.nytwords.functions.YearHits;
import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException, Exception {
        YearHits hits = new YearHits();
        hits.start();

        Chart c = new Chart();
        c.startP();
    }

   


}
