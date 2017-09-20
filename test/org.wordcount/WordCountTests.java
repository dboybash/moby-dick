/*
* Author: Shawn Bash
*
* This class contains test pertaining to the WordCount class.
*
* */

package org.wordcount;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordCountTests {

    StopWords stopWords = new StopWords();
    WordCount wordCount = new WordCount();
    Map<String, Integer> testCount = new HashMap<>();

    @Test
    public void testWordCounted() {
        String word1 = "about";
        String word2 = "annual";
        String word3 = "Annual";
        String word4 = "test-all";
        String word5 = "";
        String word6 = "annual.";
        String word7 = "http://www.gutenberg.org";
        String word8 = "about,";
        String word9 = "anytime's";

        wordCount.countWord(word1);
        wordCount.countWord(word2);
        wordCount.countWord(word3);
        wordCount.countWord(word4);
        wordCount.countWord(word5);
        wordCount.countWord(word6);
        wordCount.countWord(word7);
        wordCount.countWord(word8);
        wordCount.countWord(word9);
        assertEquals(0, wordCount.getWordCount("about"));
        assertEquals(3, wordCount.getWordCount("annual"));
        assertEquals(1, wordCount.getWordCount("test"));
        assertEquals(0, wordCount.getWordCount("all"));
        assertEquals(1, wordCount.getWordCount("gutenberg"));
        assertEquals(1, wordCount.getWordCount("org"));
        assertEquals(0, wordCount.getWordCount(","));
        assertEquals(1, wordCount.getWordCount("anytime"));
    }

    @Test
    public void testSortWordsByCount() {
        int preVal = -1;
        boolean sorted = true;

        testCount.put("word1", 1);
        testCount.put("word3", 3);
        testCount.put("word2", 2);
        testCount.put("word5", 5);
        testCount.put("word4", 4);
        assertNotEquals(0, testCount.size());

        Map<String, Integer> sortedCount = wordCount.sortByCountDesc(testCount);
        assertNotEquals(0, testCount.size());

        for (Map.Entry<String, Integer> entry : sortedCount.entrySet()) {
            if ((preVal != -1) && (preVal < entry.getValue())) {
                sorted = false;
                break;
            } else {
                preVal = entry.getValue();
            }
        }
        assertTrue(sorted);
    }

    @Test
    public void testReturnTopNWords() {

        testCount.put("testWord1", 1);
        testCount.put("testWord2", 2);
        testCount.put("testWord3", 3);
        testCount.put("testWord4", 2);
        testCount.put("testWord5", 1);
        testCount.put("testWord6", 4);

        Map<String, Integer> sortedTestMap = wordCount.sortByCountDesc(testCount);
        /*for (Map.Entry<String, Integer> entry : sortedTestMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }*/

        int testCounter = 4;
        Map<String, Integer> topWords = wordCount.getTopWords(sortedTestMap, testCounter);
        assertEquals(testCounter, topWords.size());
    }

}
