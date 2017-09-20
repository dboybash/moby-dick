/*
* Author: Shawn Bash
*
* Moby-Dick Project:
* Pick any programming language of your choice and test-drive creating a list of the top 100 most frequently occurring words (excluding stop words)
* paired with the count of occurrences of the word, found in the attached text for Herman Melville's book Moby Dick.
*
* NOTE => BE SURE STOP-WORDS.TXT AND MOBYDICK.TXT ARE COPIED TO SRC DIRECTORY OTHERWISE AND EXCEPTION WILL BE THROWN!
*
* */

package org.wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class TopOneHundredWords {

    public static void main(String[] args) throws Exception {
        // stop-words list build upon instantiation
        StopWords stopWords = new StopWords();
        WordCount wordCount = new WordCount();
        MobyDickTxt mobyDickTxt = new MobyDickTxt();

        if (stopWords.fileExists()) {
            if (mobyDickTxt.fileExists()) {
                // open the mobydick.txt file and parse each line
                // split line into words using an array
                // if word is not a stop-word then count
                try (BufferedReader reader = new BufferedReader(new FileReader(mobyDickTxt.getFileName()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] words = line.split(" ");
                        for (String word : words) {
                            if (!word.isEmpty()) {
                                wordCount.countWord(word);
                            }
                        }
                    }
                    reader.close();
                }
            } else {
                throw new Exception("Missing mobydick.txt file!  Please move mobydick.txt to src folder.");
            }
        } else {
            throw new Exception("Missing stop-words.txt file!  Please move stop-words.txt to src folder.");
        }

        // sort the list of word in descending order
        // by the value in the Map<String, Integer>
        Map<String, Integer> sortedWordListByCount = wordCount.sortByCountDesc(wordCount.getWordCount());

        // creating a new list containing only top 100 used words per requirements
        // this step could be removed by simply iterating thru the sorted list 100
        // times and print to the console.
        Map<String, Integer> topWords = wordCount.getTopWords(sortedWordListByCount, 100);

        // print top 100 words to the console
        for (Map.Entry<String, Integer> entry : topWords.entrySet()) {
            System.out.println("Word: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}