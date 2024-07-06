package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

    private final String inputString;
    private String[] wordArray;
    private final Map<String, Integer> uniqueWordsCounter;

    public WordCounter(String inputString) {
        this.inputString = inputString;
        this.uniqueWordsCounter = new HashMap<>();
    }

    public void processInput() {
        this.wordArray = inputString.split(" ");
        countUniqueWords();
    }

    private void countUniqueWords() {
        for (String word : wordArray) {
            uniqueWordsCounter.put(word, uniqueWordsCounter.getOrDefault(word, 0) + 1);
        }
    }

    public String[] getWordArray() {
        return wordArray;
    }

    public Set<String> getUniqueWords() {
        return uniqueWordsCounter.keySet();
    }

    public void printWordCounts() {
        for (Map.Entry<String, Integer> entry : uniqueWordsCounter.entrySet()) {
            System.out.printf("Слово \"%s\" повторяется %d раз(а) \n", entry.getKey(), entry.getValue());
        }
    }
}
