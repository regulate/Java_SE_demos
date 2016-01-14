package org.baddev.learning.demos.fundamentals.ref_types.negativeelements33;

import org.baddev.learning.demos.fundamentals.ref_types.elementssums32.ElementsSumsDemo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class NegativeElementsDemo {

    /**
     * @param arr array filled with positive and negative numbers
     * @return new array filled only with negative numbers
     */
    public static int[] getNegatElemArray(int [] arr) {
        return IntStream.of(arr).filter(n -> n<0).toArray();
    }

    public static void main(String[] args) {
        int [] numbers = ElementsSumsDemo.getRandFilledArray(100, 100);
        System.out.println(Arrays.toString(getNegatElemArray(numbers)));
    }

}
