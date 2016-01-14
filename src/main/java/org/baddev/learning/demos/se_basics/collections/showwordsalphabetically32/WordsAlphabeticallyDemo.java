package org.baddev.learning.demos.se_basics.collections.showwordsalphabetically32;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordsAlphabeticallyDemo {

    private static Set<String> getSetOfWords(String words) {
        Set<String> sorted = new TreeSet<>((s1,s2) ->{
            int diff = s1.compareToIgnoreCase(s2);
            if(diff==0){
                diff = s1.compareTo(s2);
            }
            return diff;
        });
        sorted.addAll(Arrays.asList(words.split(" ")));
        return sorted;
    }

    public static void printWordsInAlphabeticalOrder(String words) {
        getSetOfWords(words).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type some words separated by space:");
        printWordsInAlphabeticalOrder(sc.nextLine());
    }

}
