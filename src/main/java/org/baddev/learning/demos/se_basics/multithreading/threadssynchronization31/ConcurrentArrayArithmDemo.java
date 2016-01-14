package org.baddev.learning.demos.se_basics.multithreading.threadssynchronization31;

import java.util.Locale;

/**
 * Created by Potapchuk Ilya on 12.12.2015.
 */
public class ConcurrentArrayArithmDemo {

    private static final int[] numbers = {1, 2, 3, 4, 11, 16, 765, 33, 4, 9};
    private static long sum;
    private static long mtp = 1;

    public static void main(String[] args) {
        Thread addition = new Thread(new Runnable() {
            Thread multipl = new Thread(() -> {
                for (int i : numbers) {
                    mtp *= i;
                }
                System.out.println("Multiplication: " + mtp);
            });

            @Override
            public void run() {
                try {
                    multipl.start();
                    for (int i : numbers) {
                        sum += i;
                    }
                    multipl.join();
                    printAvgAndSum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private double calcAvg() {
                return (sum + mtp) / 2;
            }

            private void printAvgAndSum() {
                System.out.printf(Locale.US, "Sum: %d%nAverage of sum and mtp: %.2f%n", sum, calcAvg());
            }
        });
        addition.start();
    }
}
