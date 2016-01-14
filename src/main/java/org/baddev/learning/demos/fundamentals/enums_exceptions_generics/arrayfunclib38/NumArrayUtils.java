package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.arrayfunclib38;

import java.util.NoSuchElementException;

public final class NumArrayUtils {

    private NumArrayUtils() {
    }

    public static <T extends Number> int findFirstZeroIdx(T[] arr) {
        for(int i =0; i< arr.length;i++){
            if(arr[i].doubleValue()==0){
                return i;
            }
        }
        throw new NoSuchElementException("No elements with zero value found");
    }

    public static <T extends Number> int countNegatives(T[] arr){
        int count =0;
        for (T num : arr){
            if(num.doubleValue()<0){
                count++;
            }
        }
        return count;
    }


    public static <T extends Number> T findLastNegative(T[] arr){
        T last = null;
        for(T num : arr){
            if(num.doubleValue()<0){
                last = num;
            }
        }
        if(last==null){
            throw new NoSuchElementException("Negative elements not found");
        }
        return last;
    }

}
