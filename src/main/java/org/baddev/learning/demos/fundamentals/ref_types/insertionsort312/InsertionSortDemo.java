package org.baddev.learning.demos.fundamentals.ref_types.insertionsort312;

import org.baddev.learning.demos.fundamentals.ref_types.elementssums32.ElementsSumsDemo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class InsertionSortDemo {

    /**
     * Insertion sorting algorithm implementation
     *
     * @param arr non-null array to sort
     */
    public static void doInsertionSort(Integer[] arr) {
        int j = 0, curVal = 0;
        //iterate over unsorted part
        for (int i = 1; i < arr.length; i++) {
            curVal = arr[i];
            j = i;
            //iterate over sorted part, shift an intermediate elements to the right and find an insertion position
            while (j > 0 && arr[j - 1] > curVal) {
                arr[j] = arr[j - 1];
                j--;
            }
            //j is an insertion position now
            arr[j] = curVal;
        }
    }

    public static void main(String[] args) {
        Integer[] nums = IntStream.of(ElementsSumsDemo.getRandFilledArray(10, 20)).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(nums));
        doInsertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
