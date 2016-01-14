package org.baddev.learning.demos.fundamentals.syntax_constructions.eightpowers34;

import java.util.Arrays;

public class EightPowsDemo {

    private static void checkPowArg(final int n) {
        //range
        if (n < 0 || n > 10) {
            throw new IllegalArgumentException("Power value must be in 0...10 range");
        }
    }

    private static void printTime(final long nano){
        System.out.println("*Time running: "+nano+" nanoseconds");
    }

    //using class Math
    public static int[] calcPowsOfEightArithm(final int n) {
        checkPowArg(n);
        int[] result = new int[n + 1];
        final long start = System.nanoTime();
        for (int i = 0; i <= n; i++) {
            result[i] = (int) Math.pow(8, i);
        }
        final long end = System.nanoTime();
        printTime(end-start);
        return result;
    }

    //using bitwise shift
    public static int[] calcPowsOfEightBitwise(final int n) {
        checkPowArg(n);
        int[] result = new int[n + 1];
        //power of number 2 to represent number 8
        final int power = 3;
        int c = 0;
        final long start = System.nanoTime();
        for (int i = 0; i <= n*power; i+=power) {
            result[c++] = 1 << i;
        }
        final long end = System.nanoTime();
        printTime(end-start);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Arithmetic calculation:");
        System.out.println(Arrays.toString(EightPowsDemo.calcPowsOfEightArithm(5)));
        System.out.println("");
        System.out.println("Bitwise calculation:");
        System.out.println(Arrays.toString(EightPowsDemo.calcPowsOfEightBitwise(5)));
    }

}
