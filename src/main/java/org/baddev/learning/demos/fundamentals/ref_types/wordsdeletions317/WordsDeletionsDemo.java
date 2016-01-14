package org.baddev.learning.demos.fundamentals.ref_types.wordsdeletions317;

import java.util.Arrays;
import java.util.Scanner;

public class WordsDeletionsDemo {

    /**
     * Deletes words which contain certain character combination
     * @param words some combinations of characters separated by space
     * @param occasion combination of characters to be checked for presence in {@code words} param
     * @return string, consisting of words which are not contain {@code occasion} as a substring
     */
    public static String deleteWordsWithOccasion(final String words, final String occasion) {
        StringBuilder sb = new StringBuilder();
        if (words.contains(occasion) && !occasion.equals("")) {
            Arrays.asList(words.split(" "))
                    .stream()
                    .filter(n -> !n.contains(occasion))
                    .forEach(n -> sb.append(n + " "));
            //delete last space
            if(sb.length()>0)
                sb.deleteCharAt(sb.length() - 1);
        } else {
            return words;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = "";
        String occasion = "";

        System.out.println("Enter some words: ");
        words = sc.nextLine();

        System.out.println("Enter symbols combination to find in words: ");
        occasion = sc.nextLine();
        sc.close();

        System.out.println("Result: ");
        System.out.println(deleteWordsWithOccasion(words, occasion));
    }

}
