package org.baddev.learning.demos.fundamentals.ref_types.abbreviation319;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AbbreviationDemo {

    /**
     * Makes abbreviation of words.
     *
     * @param words non-null String of words separated by space
     * @return abbreviation of all the words from {@code words}
     * @throws IllegalArgumentException if {@code words} contains digits or special symbols. Allowed only characters
     * and spaces
     */
    public static String getAbbreviation(final String words) {
        //validation
        if (!Pattern.matches("[A-Za-z ]*", words)) {
            throw new IllegalArgumentException("Special characters or digits are not allowed");
        }

        String[] sepWords = words.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sepWords.length; i++) {
            if (!sepWords[i].equals(""))
                sb.append(sepWords[i].toUpperCase().charAt(0));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String words = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some words: ");
        words = sc.nextLine();
        System.out.println("Abbreviation: ");
        System.out.println(getAbbreviation(words));
    }

}
