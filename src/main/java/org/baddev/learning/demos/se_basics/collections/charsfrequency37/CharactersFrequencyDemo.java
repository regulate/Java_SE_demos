package org.baddev.learning.demos.se_basics.collections.charsfrequency37;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharactersFrequencyDemo {

    public static Map<Character, Integer> calcCharsFreq(String sentence) {
        Map<Character, Integer> charsFreq = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            if (!charsFreq.containsKey(sentence.charAt(i))) {
                charsFreq.put(sentence.charAt(i), 1);
            } else {
                charsFreq.put(sentence.charAt(i), charsFreq.get(sentence.charAt(i)) + 1);
            }
        }
        return charsFreq;
    }

    public static void main(String[] args) {
        System.out.println("Type some text:");
        String sentence = new Scanner(System.in).nextLine();
        calcCharsFreq(sentence)
                .entrySet()
                .stream()
                .sorted((e1,e2) -> e1.getKey() - e2.getKey())
                .forEach(System.out::println);
    }
}


