package org.baddev.learning.demos.fundamentals.syntax_constructions.fibonacci312;

public class FibonacciNumsDemo {

    private static long callCounter = 0;

    public static void printCallCounter() {
        System.out.println("Function was called " + callCounter + " times.");
    }

    private static void checkNumArg(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Only positive numbers and 0 are allowed");
        }
    }

    public static long findFibonacciNumRecursively(final int n) {
        callCounter++;
        checkNumArg(n);
        if (n == 0) {
            return n;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        return findFibonacciNumRecursively(n - 2) + findFibonacciNumRecursively(n - 1);
    }

    public static long findFibonacciNumWithLoop(final int n) {
        long n1 = 1, n2 = 1, result = 0;
        if (n == 1 || n == 2) {
            return 1;
        }
        for (int i = 2; i < n; i++) {
            result = n1 + n2;
            //replace two last values with next ones
            n1 = n2;
            n2 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 6; i++) {
            System.out.println(i+", "+findFibonacciNumRecursively(i));
        }
        printCallCounter();
        System.out.println("");
        for (int i = 31; i<=50; i++) {
            System.out.println(i+", "+ findFibonacciNumWithLoop(i));
        }
    }

}
