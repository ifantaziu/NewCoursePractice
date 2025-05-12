package Chapter16Homework;

import java.util.HashMap;

public class WordsFrequency {
    public static void main(String[] args) {
        String text = "Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall, All the king's horses and all the king's men could not put Humpty together again.";
        text = text.toLowerCase();
        String separation = "[,\\.\\s]";
        String[] words = text.split(separation);
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (wordFrequency.containsKey(word)) {
                wordFrequency.put(word, wordFrequency.get(word) + 1);
            } else {
                wordFrequency.put(word, 1);
            }
        }
        System.out.println(wordFrequency);
    }
}