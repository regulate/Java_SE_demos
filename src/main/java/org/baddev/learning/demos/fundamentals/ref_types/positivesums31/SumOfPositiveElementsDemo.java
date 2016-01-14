package org.baddev.learning.demos.fundamentals.ref_types.positivesums31;

import java.util.Random;

public class SumOfPositiveElementsDemo {

    public static double[] getRandomlyFilledArray(final int elementsCount) {
        if(elementsCount<=0){
            throw new IllegalArgumentException("Parameter elementsCount must be greater than 0");
        }
        double[] array = new double[elementsCount];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                array[i] = rand.nextDouble() * i;
            } else {
                array[i] = rand.nextDouble() * (-i);
            }
        }
        return array;
    }

    public static double sumOfPositiveElements(double[] arr) {
        double sum = 0;
        for (double d : arr) {
            if (d > 0) sum += d;
        }
        return sum;
    }

    public static void main(String[] args) {
        double[] arr = getRandomlyFilledArray(10000);
        System.out.println(sumOfPositiveElements(arr));
    }

}
