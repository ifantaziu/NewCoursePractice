package Chapter16Homework;

import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> dictionaryEngRo = new HashMap<>();
        System.out.println("Type an English word to get the Romanian translation (type 'exit' to quit):");
        while (true) {
            System.out.print("Enter word: ");
            String inputWord = scanner.nextLine();
            if (inputWord.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            boolean matched = false;
            for (String key : dictionaryEngRo.keySet()) {
                if (key.equalsIgnoreCase(inputWord)) {
                    System.out.println("The translation in Romanian is: " + dictionaryEngRo.get(key));
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                System.out.println("No results were found for your word.");
            }
        }
        scanner.close();
    }

    private static HashMap<String, String> getStringStringHashMap() {
        HashMap<String, String> dictionaryEngRo = new HashMap<>();
        dictionaryEngRo.putIfAbsent("Bee", "Albina");
        dictionaryEngRo.putIfAbsent("Chicken", "Pui");
        dictionaryEngRo.putIfAbsent("Dynosaurus", "Dinozaur");
        dictionaryEngRo.putIfAbsent("Flower", "Floare");
        dictionaryEngRo.putIfAbsent("Ice-cream", "Inghetata");
        dictionaryEngRo.putIfAbsent("Lion", "Leu");
        dictionaryEngRo.putIfAbsent("Rainbow", "Curcubeu");
        dictionaryEngRo.putIfAbsent("Unicorn", "Unicorn");
        dictionaryEngRo.putIfAbsent("Watermelon", "Pepene verde");
        dictionaryEngRo.putIfAbsent("Zebra", "Zebra");
        return dictionaryEngRo;
    }
}