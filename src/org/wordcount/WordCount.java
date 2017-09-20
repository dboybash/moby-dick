/*
* Author: Shawn Bash
*
* This class handles all aspects of counting all words not included in the stop-words list.
* It receives a string from the TopOneHundredWords.java class and determines if its is a variant
* of a word (plural, compound, etc...).  If so, the word is transformed into it's base form and
* checked against the stop-words list contained in the StopWords class.  Counted words are handled
* with a HashMap<K,V> data structure.  The sortByCountDesc() method sorts the word Map by value
* (count) and the getTopWords() method returns a map of the top Nth words (where Nth is determined
* by input parameter).
*
* */

package org.wordcount;

import java.util.*;
import java.util.regex.Pattern;

public class WordCount {

    private final Map<String, Integer> words = new HashMap<>();
    StopWords stopWords = new StopWords();
    static final Pattern nonWordChar = Pattern.compile("\\W|^$");

    public WordCount() {
    }

    //accepts string from TopOneHundredWords class
    //if non-word character found then parse/transform to base word
    //check if word is found in stop-words.txt file by StopWords class
    //if not found then addWord()
    public void countWord(String string) {
        if (nonWordChar.matcher(string).find()) {
            String[] words = string.replaceAll("^http://www", "")
                    .replaceAll("'s", "")
                    .replaceAll(",", "")
                    .replaceAll("[^a-zA-Z0-9]+", "")
                    .split(" ");
            for (String x : words) {
                //System.out.println("split word = " + x);
                if (!stopWords.isStopWord(x)) {
                    addWord(x);
                }
            }
        } else {
            //System.out.println("string else loop = " + string);
            if (!stopWords.isStopWord(string)) {
                addWord(string);
            }
        }
    }

    //if word found in word Map increment value by 1
    //else add word to Map and insert 1 for value
    //to negate case, words are added in LowerCase form
    private void addWord(String word) {
        if (!words.containsKey(word.toLowerCase())) {
            words.put(word.toLowerCase(), 1);
        } else {
            words.put(word.toLowerCase(), words.get(word.toLowerCase()) + 1);
        }
    }

    //return count for specific word
    public int getWordCount(String word) {
        if (words.containsKey(word.toLowerCase())) {
            return words.get(word.toLowerCase());
        } else {
            return 0;
        }
    }

    //return complete word Map
    public Map<String, Integer> getWordCount() {
        return words;
    }

    //takes Map and sorts Value by descending order
    public Map<String, Integer> sortByCountDesc(Map<String, Integer> unsortedCount) {

        // HashMap stores each key and value pair as an Entry<K,V> object
        // By making use of this Entry<K,V> object, we can sort the HashMap based on values
        // Retrieve the entry set from HashMap like below
        Set<Map.Entry<String, Integer>> unsortedSet = unsortedCount.entrySet();

        // Create a LinkedList from the above mapEntries
        // use linked list because insertion of elements in linked list is faster than an array list
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<>(unsortedSet);

        // Sort the linked list using Collections.sort() method, by passing LinkedList and a custom comparator
        Collections.sort(sortedList, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue()
                .compareTo(o1.getValue()));

        // Now that the LinkedList is sorted, and we need to store the key, value pairs into the new Map
        // A HashMap doesn't preserve the order, so use LinkedHashMap
        Map<String, Integer> sortedCount = new LinkedHashMap<>();
        sortedList.forEach((entry) -> {
            sortedCount.put(entry.getKey(), entry.getValue());
        });
        return sortedCount;
    }

    //returns a Map for Nth number of top words (100 for this exercise)
    public Map<String, Integer> getTopWords(Map<String, Integer> sortedCount, int numberOfTopWords) {
        int count = 0;
        Map<String, Integer> topWords = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedCount.entrySet()) {
            if (count < numberOfTopWords && count <= sortedCount.size()) {
                topWords.put(entry.getKey(), entry.getValue());
                count++;
            }
        }
        return topWords;
    }
}

