package org.baddev.learning.demos.se_basics.collections.quoteswithbrackets;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BracketsDemo {

    public static void checkBrackets(String s) {
        Deque<AbstractMap.SimpleEntry<Integer, Character>> brackets = new ArrayDeque<>();
        Deque<Character> quotes = new ArrayDeque<>();
        int unclQuoteIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    if (quotes.isEmpty()) {
                        brackets.push(new AbstractMap.SimpleEntry<Integer, Character>(i+1, '('));
                    }
                    break;
                case ')':
                    if (quotes.isEmpty() && brackets.poll() == null) {
                        System.out.println("*Unnecessary closing bracket found at position " + (i+1));
                    }
                    break;
                case '"':
                    quotes.push('"');
                    if (quotes.size() % 2 == 0) {
                        quotes.clear();
                        unclQuoteIdx = 0;
                    } else {
                        unclQuoteIdx = i;
                    }
                    break;
            }
        }
        if (!brackets.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("*" + brackets.size() + " unclosed round brackets found at positions: ");
            brackets.stream().mapToInt(e -> e.getKey()).sorted().forEach(k -> sb.append(k + " "));
            sb.trimToSize();
            System.out.println(sb.toString());
        }
        if (!quotes.isEmpty()) {
            System.out.println("*Unclosed quotes found at position " + (unclQuoteIdx+1));
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter text which contains round brackets and/or quotes: ");
        checkBrackets(new Scanner(System.in).nextLine());
    }

}
