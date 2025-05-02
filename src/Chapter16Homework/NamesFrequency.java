package Chapter16Homework;

import java.util.ArrayList;
import java.util.HashMap;

public class NamesFrequency {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Maria");
        names.add("Alex");
        names.add("Nick");
        names.add("Elisa");
        names.add("John");
        names.add("Maria");
        names.add("John");
        names.add("Max");
        names.add("Maria");
        names.add("John");
        names.add("Maria");
        HashMap<String, Integer> namesFrequency = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (namesFrequency.containsKey(name)) {
                int count = namesFrequency.get(name);
                namesFrequency.put(name, count + 1);
            } else {
                namesFrequency.put(name, 1);
            }
        }
        System.out.println(namesFrequency);
    }
}