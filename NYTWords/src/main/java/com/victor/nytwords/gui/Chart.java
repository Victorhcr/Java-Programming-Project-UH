package com.victor.nytwords.gui;

import com.victor.nytwords.filehandler.RelatedWords;
import com.victor.nytwords.functions.Articles;
import com.victor.nytwords.listener.OpenUrlActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

public class Chart extends JFrame {

    JButton readMore = new JButton("Read More!");
    private Articles hl;
    private String word;
    private int beg;
    private int end;
    private String[] args;
    private FileReader fileNorm;
    private FileReader filePred;
    private JFreeChart chart;

    public Chart() throws FileNotFoundException, IOException {
        super("New York Times Articles");

        FileReader main = new FileReader("files/words_data/main.txt");
        getData(main);
        hl = new Articles(this.word, this.beg, this.end);
        hl.start();

        this.fileNorm = new FileReader("files/words_data/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        this.filePred = new FileReader("files/words_data/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");

        try {

            XYSeries series1 = new XYSeries("Word: " + this.word);
            addData(series1, this.fileNorm);

            XYSeries series2 = new XYSeries("Prediction");
            addData(series2, this.filePred);

            XYSeriesCollection xyDataset = new XYSeriesCollection();
            xyDataset.addSeries(series1);
            xyDataset.addSeries(series2);

            chart = ChartFactory.createXYLineChart("Article Hits Chart", "Years", "Percentage of Articles", xyDataset, PlotOrientation.VERTICAL, true, false, false);
            chart.setBackgroundPaint(Color.yellow);

            XYPlot plot = (XYPlot) chart.getPlot();
            setPlot(plot);

            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);

            addArticle();
            addRelatedWords();

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(900, 600));

            this.setLayout(new BorderLayout());
            this.add(chartPanel, BorderLayout.CENTER);
            if (hl.getData()[2] != null) {
                OpenUrlActionListener buttonListener = new OpenUrlActionListener(hl.getData()[2]);
                readMore.addActionListener(buttonListener);
                this.add(readMore, BorderLayout.SOUTH);
            }
            this.pack();

            this.setVisible(true);
        } catch (Exception i) {
            System.out.println(i);
        }
    }

    public void addRelatedWords() throws FileNotFoundException {
        RelatedWords rl = new RelatedWords(this.word);
        int counter = 1;
        int max = 3;
        Set rlKeySet = rl.getRelatedWords().keySet();
        if (!rlKeySet.isEmpty()) {
            TextTitle textRelatedWordIntro = new TextTitle("    Related words:");
            //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
            textRelatedWordIntro.setPosition(RectangleEdge.BOTTOM);
            textRelatedWordIntro.setHorizontalAlignment(HorizontalAlignment.LEFT);
            chart.addSubtitle(0, textRelatedWordIntro);
            for (Object word : rlKeySet) {
                TextTitle textRelatedWord = new TextTitle("      " + word);
                //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
                textRelatedWord.setPosition(RectangleEdge.BOTTOM);
                textRelatedWord.setHorizontalAlignment(HorizontalAlignment.LEFT);
                chart.addSubtitle(0, textRelatedWord);

                counter++;
                if (counter > max) {
                    break;
                }
            }
        } else {
            TextTitle textNoWords = new TextTitle("\n    No related words available.");
            //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
            textNoWords.setPosition(RectangleEdge.BOTTOM);
            textNoWords.setHorizontalAlignment(HorizontalAlignment.LEFT);
            chart.addSubtitle(0, textNoWords);
        }

        TextTitle textEmpty = new TextTitle("\n");
        //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textEmpty.setPosition(RectangleEdge.BOTTOM);
        textEmpty.setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.addSubtitle(0, textEmpty);
    }

    public void addArticle() {
        if (hl.getData()[0] != null) {
            TextTitle textHeadline = new TextTitle("\n    Headline: " + hl.getData()[0]);
            //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
            textHeadline.setPosition(RectangleEdge.BOTTOM);
            textHeadline.setHorizontalAlignment(HorizontalAlignment.LEFT);
            chart.addSubtitle(0, textHeadline);

            TextTitle textSummary;
            if (!hl.getData()[1].equals("null")) {
                textSummary = new TextTitle("\n    Summary: " + hl.getData()[1]);
            } else {
                textSummary = new TextTitle("\n    No summary available.");
            }
            //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
            textSummary.setPosition(RectangleEdge.BOTTOM);
            textSummary.setHorizontalAlignment(HorizontalAlignment.LEFT);
            chart.addSubtitle(0, textSummary);
        } else {
            TextTitle textLink = new TextTitle("\n    No articles available.");
            //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
            textLink.setPosition(RectangleEdge.BOTTOM);
            textLink.setHorizontalAlignment(HorizontalAlignment.LEFT);
            chart.addSubtitle(0, textLink);
        }
        TextTitle textEmpty = new TextTitle("\n");
        //textTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textEmpty.setPosition(RectangleEdge.BOTTOM);
        textEmpty.setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.addSubtitle(0, textEmpty);
    }

    /**
     * This method sets the plot characteristics
     *
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
