package org.baddev.learning.demos.fundamentals.ref_types.customarraysort34;

import org.baddev.learning.demos.fundamentals.ref_types.elementssums32.ElementsSumsDemo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CustomArraySortDemo {

    /**
     * Method copies and sorts an array in a custom way. First four elements sorted naturally and the last four
     * elements sorted in a descending order.
     *
     * @param arr an array to sort
     * @return sorted array
     */
    public static Integer[] getSortedArray(final Integer[] arr) {
        //validation
        if (arr.length < 10) {
            throw new IllegalArgumentException("Array must be non-null and consist of at least 10 elements");
        }
        //copy
        Integer[] nums = Arrays.copyOf(arr, arr.length);
        Integer[] firstFour = Arrays.copyOfRange(nums, 0, 4);
        Integer[] lastFour = Arrays.copyOfRange(nums, nums.length - 4, nums.length);
        //selectionSort
        Arrays.sort(firstFour);
        doBubbleSort(lastFour, false);
        //copy
        System.arraycopy(firstFour, 0, nums, 0, firstFour.length);
        System.arraycopy(lastFour, 0, nums, nums.length - 4, lastFour.length);
        return nums;
    }

    /**
     * Bubble sorting algorithm. Not optimized for sorted arrays.
     *
     * @param arr     an array to sort
     * @param natural true if natural sorting order, false if descending
     */
    public static void doBubbleSort(Integer[] arr, boolean natural) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < (arr.length - i); j++) {
                if (natural ? arr[j - 1] > arr[j] : arr[j - 1] < arr[j]) {
                    arr[j] = arr[j] + arr[j - 1];
                    arr[j - 1] = arr[j] - arr[j - 1];
                    arr[j] = arr[j] - arr[j - 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = IntStream.of(ElementsSumsDemo.getRandFilledArray(10, 50))
                .boxed()
                .toArray(Integer[]::new);
        System.out.println("Original array: " + Arrays.toString(nums));
        System.out.println("Sorted array:   " + Arrays.toString(getSortedArray(nums)));
    }

}
