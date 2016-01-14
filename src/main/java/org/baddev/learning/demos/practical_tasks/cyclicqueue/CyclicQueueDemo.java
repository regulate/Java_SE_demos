package org.baddev.learning.demos.practical_tasks.cyclicqueue;

import java.text.Collator;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;

public final class CyclicQueueDemo {

    private static final String regexp = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
    private static Queue<String> strings;
    private static Queue<Integer> ints;

    private static int initStringsQueue(final String regexp, final String... values) {
        if (values.length < 1)
            throw new IllegalStateException();
        int c = 0;
        strings = new CyclicQueue<String>(values.length);
        for (int i = 0; i < values.length; i++) {
            if (values[i].matches(regexp)) {
                strings.offer(values[i]);
                c++;
            }
        }
        return c;
    }

    private static void initIntegersQueue(final int count, final int bound) {
        Random r = new Random();
        ints = new CyclicQueue<Integer>(count);
        for (int i = 0; i < count; i++) {
            ints.offer(r.nextInt(bound));
        }
    }

    private static void printSortedWithCollator(Queue<String> queue, Locale lc) {
        Collator collator = Collator.getInstance(lc);
        System.out.println("\nSorted with collator:");
        queue.stream().sorted((e1, e2) -> collator.compare(e1, e2)).forEach(System.out::println);
    }

    public static void main(String[] args) {
        initIntegersQueue(4, 50);
        System.out.println(ints);
        System.out.printf("%nNames added: %d%n%s%n", initStringsQueue(regexp ,"John Mensah", "Michael Cunnings",
                "Craig Dawson", "Ben Watson", "Bucky"), strings);
        printSortedWithCollator(strings, Locale.US);
    }

}
