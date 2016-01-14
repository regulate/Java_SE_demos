package org.baddev.learning.demos.fundamentals.ref_types.elementssums32;

import java.util.Random;

public class ElementsSumsDemo {

    /**
     * @param elementsCount size of array
     * @param bounds        upper and lower bounds of generated pseudo-random numbers.
     * @return array initialized with pseudo-random values
     * @throws IllegalArgumentException if <code>elementsCount <= 0 || bounds <= 0</code>
     */
    public static int[] getRandFilledArray(int elementsCount, int bounds) {
        checkArgs(elementsCount, bounds);
        int[] arr = new int[elementsCount];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(bounds*2+1)- bounds;
        }
        return arr;
    }

    /**
     * @param arr          array filled with integers
     * @param delimiterIdx index of array element which defines position where array logically splits on two groups:
     *                     before and after the value
     * @return true if <code>sumBefore</code> &gt <code>sumAfter</code>, false otherwise
     * @throws IllegalArgumentException if <code>(delimiterIdx >= arr.length-1 && arr.length>=3) || delimiterIdx <= 0
     *                                  || arr.length &lt 3</code>
     */
    public static boolean isBeforeSumGTAfter(final int[] arr, final int delimiterIdx) {
        checkArgs(arr, delimiterIdx);
        long sumBefore = 0L, sumAfter = 0L;
        for (int i = 0; i < arr.length; i++) {
            if (i < delimiterIdx) {
                sumBefore += arr[i];
            } else if (i > delimiterIdx) {
                sumAfter += arr[i];
            }
        }
        System.out.println("Sum of elements before " + delimiterIdx + " position: " + sumBefore);
        System.out.println("Sum of elements after " + delimiterIdx + " position: " + sumAfter);
        return sumBefore > sumAfter;
    }

    private static void checkArgs(final int[] arr, final int delimiterIdx) {
        //validation
        if(arr==null){
            throw new IllegalArgumentException("Parameter arr can't be null");
        }
        final int minSize = 3;
        final int upBound = arr.length - 1;
        if (delimiterIdx <= 0) {
            throw new IllegalArgumentException("Value of parameter delimiterIdx must be greater than 0");
        } else if (delimiterIdx >= arr.length - 1 && arr.length >= minSize) {
            throw new IllegalArgumentException("Value of parameter delimiterIdx must be in 1..." + upBound + " bounds");
        } else if (arr.length < minSize) {
            throw new IllegalArgumentException("Array length must be greater than or equal to 3");
        }
    }

    private static void checkArgs(int elementsCount, int bounds){
        //validation
        if (elementsCount <= 0) {
            throw new IllegalArgumentException("Parameter elementsCount must be greater than 0");
        } else if(bounds <= 0){
            throw new IllegalArgumentException("Parameter bounds must be greater than 0");
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandFilledArray(3, 10);
        int delimiterIdx = 1;
        System.out.println("Is sum before delimiter gt after? " + isBeforeSumGTAfter(arr, delimiterIdx));
    }

}
