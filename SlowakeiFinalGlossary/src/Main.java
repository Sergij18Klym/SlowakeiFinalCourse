import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classname: Main
 *
 * @version     23 June 2020
 * @author      Klymenko Sergij, ONPU
 *
 * GLOSSARY - 10 points
 *
 * 1.1.  Download a text about Harry Potter.
 * 1.2.  For each distinct word in the text calculate the number of occurrence.
 * 1.3.  Use RegEx.
 * 1.4.  Sort in the DESC mode by the number of occurrence.
 * 1.5.  Find the first 20 pairs.
 * 1.6.  Find all the proper names
 * 1.7.  Count them and arrange in alphabetic order.
 * 1.8.  First 20 pairs and names write into to a file test.txt
 * 1.9.  Create a fine header for the file
 * 1.10. Use Java Collections to demonstrate your experience (Map, List)
 *
 * Show all your skills and experience.  All the tricks will be taken into account.
 *
 * */

public class Main {

    public static void main(String[] args) throws IOException {

        String text = new String(Files.readAllBytes(Paths.get("D:\\Docs\\Books\\HarryPotterSorcerer.txt")));

        /**
         * Using RegEx to clean the text
         */
        String cleanedText = text
                .replaceAll("\\.", "")
                .replaceAll(",", "")
                .replaceAll(";", "")
                .replaceAll(":", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\"", "")
                .replaceAll("\\/", "")
                .replaceAll("\\?", "")
                .replaceAll("!", "")
                .replaceAll("\\*", "")
                .replaceAll("~", "")
                .replaceAll("\\n", " ")
                .replaceAll("\\d", "")
                .replaceAll("\\r", "")
                .replaceAll("\uFEFF", "")
                .replaceAll("\\“", "")
                .replaceAll("–", "")
                ;

        /**
         * Assigning the split cleaned text to an ArrayList
         */
        List<String> splitList = new ArrayList<>(Arrays.asList(cleanedText.split(" ")));

        /**
         * Filtering the non-words items in the ArrayList
         */
        List<String> filteredList = splitList.stream()
                .filter(line -> (!line.contains("-")))
                .filter(line -> (!line.equals("")))
                .filter(line -> (!line.contains("'")))
                .collect(Collectors.toList());

        /**
         * Assigning all the distinct words depending on the number of occurrence
         * from an ArrayList to the LinkedHashMap in order of occurrence in text
         */
        LinkedHashMap<String, Integer> HarryMap = new LinkedHashMap<>();
        
        for (int i = 0; i < filteredList.size(); i++) {
            String tempWord = filteredList.get(i);

            if (!HarryMap.containsKey(tempWord)) {
                HarryMap.put(tempWord, 1);
            } else {
                HarryMap.put(tempWord, HarryMap.get(tempWord) + 1);
            }
        }

        /**
         * Assigning all the distinct words to a sorted LinkedHashList in descending order
         * depending on the value of its occurrence
         */
        LinkedHashMap<String, Integer> HarryMapDesc = new LinkedHashMap<>();

        HarryMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(xxx -> HarryMapDesc.put(xxx.getKey(), xxx.getValue()));

        System.out.println("\n-----------------------------------------------");
        System.out.println("All the distinct words in descending order:");
        System.out.println("-----------------------------------------------\n");

        for (Map.Entry<String, Integer> entry : HarryMapDesc.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", \t\t\tOccurrence = " + entry.getValue());
        }

        for(int j = 0; j < 200; j++) {
            System.out.println("---------------------------------------");
        }

        /**
         * Finding in the unsorted LinkedHashMap all the words
         * that occur in the text only written with the first capital letter
         * and assigning these keys to a Map in alphabetic order
         */
        LinkedHashMap<String, Integer> properName = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> properNameAlphabet = new LinkedHashMap<>();
        
        for (String key : HarryMap.keySet()) {
            String tempWord = key;
            String tempWordLow = tempWord.toLowerCase();

            if (!HarryMap.containsKey(tempWordLow)) {
                properName.put(tempWord, HarryMap.get(tempWord));
            }
        }

        properName.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(xxx -> properNameAlphabet.put(xxx.getKey(), xxx.getValue()));

        System.out.println("\n-----------------------------------------------");
        System.out.println("All the proper names in alphabetic order:");
        System.out.println("-----------------------------------------------\n");

        for (Map.Entry<String, Integer> entry : properNameAlphabet.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", \t\t\tOccurrence = " + entry.getValue());
        }

        /**
         * Counting all the found proper names
         */
        int countName = (int) properNameAlphabet.entrySet().stream().count();

        System.out.println("\n-----------------------------------------------");
        System.out.println("The total quantity of proper names: " + countName);
        System.out.println("-----------------------------------------------\n");

        /**
         * Finding 20 first proper names and 20 first distinct words
         */
        LinkedHashMap<String, Integer> first20Words = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> first20Names = new LinkedHashMap<>();

        HarryMapDesc.entrySet().stream().limit(20)
                .forEachOrdered(xxx -> first20Words.put(xxx.getKey(), xxx.getValue()));

        properNameAlphabet.entrySet().stream().limit(20)
                .forEachOrdered(xxx -> first20Names.put(xxx.getKey(), xxx.getValue()));

        System.out.println("\n-----------------------------------------------");
        System.out.println("The first 20 most common distinct words:");
        System.out.println("-----------------------------------------------\n");

        for (Map.Entry<String, Integer> entry : first20Words.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", \t\t\tOccurrence = " + entry.getValue());
        }

        System.out.println("\n-----------------------------------------------");
        System.out.println("The first 20 proper names in alphabetic order:");
        System.out.println("-----------------------------------------------\n");

        for (Map.Entry<String, Integer> entry : first20Names.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", \t\t\tOccurrence = " + entry.getValue());
        }

        /**
         * The first 20 distinct words and proper names are being written into a text file
         */
        String stringToFile = "The first 20 most common distinct words:" + System.lineSeparator() +
                "-----------------------------------------" + System.lineSeparator() +
                "Word\t\t\tOccurrence" + System.lineSeparator() +
                "-----------------------------------------" + System.lineSeparator();

        for (String key : first20Words.keySet()) {
            stringToFile += key + "\t\t\t" + first20Words.get(key) + System.lineSeparator();
        }

        stringToFile += System.lineSeparator() +
                "The first 20 proper names in alphabetic order:" + System.lineSeparator() +
                "-----------------------------------------" + System.lineSeparator() +
                "Word\t\t\tOccurrence" + System.lineSeparator() +
                "-----------------------------------------" + System.lineSeparator();

        for (String key : first20Names.keySet()) {
            stringToFile += key + "\t\t\t" + first20Names.get(key) + System.lineSeparator();
        }

        Path path = Paths.get("E:\\TEST-OUTPUT.txt");
        Files.write(path, stringToFile.getBytes());

    }
}
