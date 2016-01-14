package org.baddev.learning.demos.fundamentals.ref_types.selectionsort311;

import org.baddev.learning.demos.fundamentals.ref_types.elementssums32.ElementsSumsDemo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SelectionSortDemo {

    /**
     * Selection sorting algorithm implementation
     *
     * @param arr non-null array to sort
     */
    public static void doSelectionSort(Integer[] arr) {
        int idx = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            idx = i;
            //searching for index of min value
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            //swap
            if (idx != i || !arr[idx].equals(arr[i])) {
                arr[i] = arr[i] + arr[idx];
                arr[idx] = arr[i] - arr[idx];
                arr[i] = arr[i] - arr[idx];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = IntStream.of(ElementsSumsDemo.getRandFilledArray(12, 15)).boxed().toArray(Integer[]::new);
        System.out.println("Original: " + Arrays.toString(nums));
        doSelectionSort(nums);
        System.out.println("Sorted: " + Arrays.toString(nums));
    }

}
