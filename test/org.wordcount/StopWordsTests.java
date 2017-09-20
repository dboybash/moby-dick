/*
* Author: Shawn Bash
*
* This class contains test pertaining to the StopWords class.
*
* */

package org.wordcount;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StopWordsTests {

    StopWords stopWords = new StopWords();
    File file;

    @Before
    public void setUp() {
        file = new File("stop-words.txt");
    }

    @Test
    public void testStopWordsFileExists() {
        assertEquals(true, file.exists());
    }

    @Test
    public void testStopWordsFileNotEmpty() {
        assertNotEquals(0, file.length());
    }

    @Test
    public void testIsStopWord() {
        String word1 = "about";
        String word2 = "below";
        String word3 = " ";
        String word4 = "ABOUT";
        String word5 = "all";

        assertEquals(true, stopWords.isStopWord(word1));
        assertEquals(false, stopWords.isStopWord(word2));
        assertEquals(false, stopWords.isStopWord(word3));
        assertEquals(true, stopWords.isStopWord(word4));
        assertEquals(true, stopWords.isStopWord(word5));
    }
}
