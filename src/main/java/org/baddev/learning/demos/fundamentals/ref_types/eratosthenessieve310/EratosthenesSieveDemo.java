package org.baddev.learning.demos.fundamentals.ref_types.eratosthenessieve310;

import java.util.stream.IntStream;

public class EratosthenesSieveDemo {

    public static int[] getPosSeqFilledArr(int elementsCount) {
        //validation
        if(elementsCount<=0){
            throw new IllegalArgumentException("Param elementsCount must be gt 0");
        }

        int[] arr = new int[elementsCount];
        for (int i = 0, j = 1; i < elementsCount; i++, j++) {
            arr[i] = j;
        }
        return arr;
    }

    public static void printPrimes(int[] arr) {
        if(arr==null){
            throw new IllegalArgumentException("Array must be non-null");
        }
        //eratosthenes sieve
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = -1;
            } else if (arr[i] % 2 == 0 && arr[i] != 2) {
                arr[i] = -2;
            } else {
                for (int j = 3; j * j <= arr[i]; j += 2) {
                    if (arr[i] % j == 0) {
                        arr[i] = -3;
                        break;
                    }
                }
            }
            if (arr[i] > 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println("");
        System.out.println(IntStream.of(arr).filter(n -> n>0).count()+" prime numbers found.");
    }

    public static void main(String[] args) {
        printPrimes(getPosSeqFilledArr(3));
    }

}
