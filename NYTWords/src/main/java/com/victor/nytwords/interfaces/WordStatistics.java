/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.interfaces;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Victorhcr
 */
public interface WordStatistics {
    public String getWordByIndexOrder(int i);
    public void replaceSelectedWordLine(String word, Integer times);
    public void addNewWord(String word) throws IOException;
    public LinkedHashMap sortHashMapByValues(HashMap passedMap);
    public void updateWordsStatisticsFile(String word) throws IOException;
}
