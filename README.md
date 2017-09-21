# Source Allies Tech Showcase - Moby Dick
Author - Shawn Bash
Purpose - Top 100 words exercise for Source Allies


About:
The purpose of this project was to use TDD principles to create a list of the top 100 most frequently occurring words (excluding stop words) paired with the count of occurrences of the word, found in the attached text for Herman Melville's book Moby Dick.  I approached this exercise by building classes that pertained to the required text files first, "mobydick.txt" and "stop-words.txt".  Since these files were essential to the exercise, I wanted to make sure those files were present in the src directory and contained data.  For the stop words, I used the StopWords class to create and contain a list of the stop words to be used by the program.  The next problem I worked through was how to count the words.  For that I created the WordCount class and decided to use a HashMap data structure to house the word count due to its ability to contain a key and a value.  In this instance, the key was the word and the value was the count.  Finally, I created a main class called TopOneHundredWords to bring everything together and produce the specified output.  

Program Notes:
Below are the steps to successfully run the TopOneHundredWords class:
1. download "MobyDickTxt.java", "StopWords.java", "TopOneHundredWords.java", "WordCount.java" to org.wordcount package in src directory.
2. download "MobyDickTxtTests.java", "StopWordsTests.java", and "WordCountTest.java" to org.wordcount package in a test directory.
3. copy "stop-words.txt" and "mobydick.txt" to the src directory.
4. run TopOneHundredWords.java file.

*DISCLAIMER - this is my first attempt at uploading a project to GitHub using Intellij's standard .gitignore file.  If any files are needed, please send me a message.
