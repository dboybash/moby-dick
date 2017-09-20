/*
* Author: Shawn Bash
*
* This class handles all aspects of the stop-words list.
* When this class is instantiated, a class variable of type
* list is populated, by the loadStopWordList in the constructor,
 * from the stop-words.txt file.
*
* */

package org.wordcount;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StopWords {

    private final String fileName = "stop-words.txt";
    File file = new File(fileName);
    private static List<String> stopWords = new ArrayList<>();

    //constructor populates class variable of type List<>
    public StopWords() {
        this.stopWords = loadStopWordList();
    }

    public boolean fileExists() {
        return this.file.exists();
    }

    //method used in the WordCount class to see if string param is found in stop-word list
    public boolean isStopWord(String word) {
        return stopWords.stream().anyMatch(word::equalsIgnoreCase);
    }

    //called in the constructor to build stop-list upon instantiation
    private List<String> loadStopWordList() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stopWords = (List<String>) stream
                    .filter(line -> !line.startsWith("#"))
                    .filter(line -> line.length() > 1)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopWords;
    }
}