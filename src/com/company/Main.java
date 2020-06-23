/*
 *
 * Classname: Main
 *
 * @version 22.06.2020
 * @author Vladyslav Zaichenko

 * Module 4 Final Task
 *
 * 1. GLOSSARY - 10 points
 *
 * 1.1. Download a text about Harry Potter.
 *
 * 1.2. For each distinct word in the text calculate the number of occurrence.
 *
 * 1.3. Use RegEx..
 *
 * 1.4. Sort in the DESC mode by the number of occurrence..
 *
 * 1.5. Find  the first 20 pairs.
 *
 * 1.6  Find all the proper names
 *
 * 1.7.  Count them and arrange in alphabetic order.
 *
 * 1.8.  First 20 pairs and names write into to a file test.txt
 *
 * 1.9.  Create a fine header for the file
 *
 * 1.10  Use Java  Collections to demonstrate your experience (Map, List)
 *
 * Show all your skills and experience.
 * All the tricks will be taken into account.
 *
 * Zaichenko Vladyslav KNUTE
 *
 */

package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    // 1.1. Download a text about Harry Potter.
    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get
                ("/Users/Vladyslav/Desktop/harry.txt")));


    // 1.2. For each distinct word in the text calculate the number of occurrence.
    // 1.7.  Count them and arrange in alphabetic order.
    // 1.3. Use regular expression java

        String word = "";
        int counter = 1;
        String[] words = text.toLowerCase()
                .replaceAll("[^a-zA-Z0-9' ]", "")
                .replaceAll("\\.", "")
                .replaceAll(",", "")
                .replaceAll("\"", "")
                .replaceAll("", "")
                .replaceAll("/", "")
                .replaceAll("!", "")
                .replaceAll(";", "")
                .replaceAll(":", "")
                .replaceAll("\\?", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\"", "")
                .replaceAll("\\\\", "")
                .replaceAll("\n", "")
                .replaceAll("�", "")
                .replaceAll("--", "")
                .replaceAll(" -", "")
                .replaceAll(" - ", "")
                .replaceAll("- ", "")
                .replaceAll("' ", "")
                .replaceAll(" '", "")
                .replaceAll(" ' ", "")
                .replaceAll("'", "")
                .replaceAll("1", "")
                .replaceAll("2", "")
                .replaceAll("3", "")
                .replaceAll("4", "")
                .replaceAll("5", "")
                .replaceAll("6", "")
                .replaceAll("7", "")
                .replaceAll("8", "")
                .replaceAll("9", "")
                .replaceAll("0", "")
                .replaceAll("", "")
                .toLowerCase().split("\\s");
        Arrays.sort(words);
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals(words[i - 1])) {
                counter++;
            } else if (!words[i].equals(words[i - 1])) {
                word = words[i - 1];
                System.out.println("Word '" + word + "' occur in the text: " + counter + " times");
                counter = 1;
            }
            word=words[i-1];
        }

        // 1.2. For each distinct word in the text calculate the number of occurrence.
        Map<String, Integer> distinctWords = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if(!distinctWords.containsKey(words[i])){
                distinctWords.put(words[i],1);
            }else{
                Integer counter2 = distinctWords.get(words[i]);
                distinctWords.put(words[i], counter2+1);
            }
        }
        distinctWords.forEach((item1,item2)->{
            System.out.println("Word '" + item1 + "' occur in the text: " + item2 + " times");
        });

        // 1.4. Sort in the DESC mode by the number of occurrence..
        Map<String , Integer> occuredList = distinctWords.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        occuredList.forEach((key,value)->{
            System.out.println("Word '" + key + "' occur in the text: " + value + " times");
        });

        // 1.5. Find  the first 20 pairs.
        List<String> keys = new ArrayList<>();
        Map<String, Integer> topWords20 = new LinkedHashMap<>();
        occuredList.keySet().stream().forEach(key-> keys.add(key));
        for (int i = 0; i < 20; i++) {
            topWords20.put(keys.get(i), occuredList.get(keys.get(i)));
        }
        topWords20.forEach((key,value)->{
            System.out.println("Word '" + key + "' occur in the text: " + value + " times");
        });


        // 1.8.  First 20 pairs and names write into to a file test.txt
        // 1.9.  Create a fine header for the file
        try(FileWriter writer = new FileWriter("harry.txt",true)) {
            writer.write("FINE HEADER FOR THE FILE \n" + String.valueOf(topWords20));
        }





    }
}
