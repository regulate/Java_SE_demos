package org.baddev.learning.demos.se_basics.multithreading.sortingwiththreads32;

import org.baddev.learning.demos.fundamentals.ref_types.customarraysort34.CustomArraySortDemo;
import org.baddev.learning.demos.fundamentals.ref_types.insertionsort312.InsertionSortDemo;
import org.baddev.learning.demos.fundamentals.ref_types.selectionsort311.SelectionSortDemo;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by Potapchuk Ilya on 12.12.2015.
 */
public class ArraySortingDemo {

    private static Integer[] numbers = {6, 4, 5, 1, 3, 9, 5, 2};

    public static synchronized void printNumbers() {
        System.out.println(Arrays.toString(numbers));
    }

    private static void printSortedArray(){
        System.out.printf("Sorted array: %s%n%n", Arrays.toString(numbers));
    }

    public static void sort() {

        class SortingTask implements Runnable {
            Consumer<Integer[]> func;
            String algName;
            long delay;

            public SortingTask(String algName, long delay, Consumer<Integer[]> function) {
                this.func = function;
                this.algName = algName;
                this.delay = delay;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                    long start = System.nanoTime();
                    func.accept(numbers);
                    System.out.printf(algName + ": %d ns%n", System.nanoTime() - start);
                    printNumbers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Thread bubble = new Thread(new SortingTask("Bubble sort", 20, n -> CustomArraySortDemo.doBubbleSort(n, false)));
        Thread insertion = new Thread(new SortingTask("Insertion sort", 30, InsertionSortDemo::doInsertionSort));
        Thread selection = new Thread(new SortingTask("Selection sort", 10, SelectionSortDemo::doSelectionSort));
        bubble.start();
        insertion.start();
        selection.start();
    }

    public static void main(String[] args) {
        try {
            System.out.printf("Original array: %s%n%n", Arrays.toString(numbers));
            sort();
            Thread.sleep(11);
            printSortedArray();
            Thread.sleep(10);
            printSortedArray();
            Thread.sleep(10);
            printSortedArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
