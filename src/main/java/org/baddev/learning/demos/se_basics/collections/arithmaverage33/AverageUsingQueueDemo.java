package org.baddev.learning.demos.se_basics.collections.arithmaverage33;

import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class AverageUsingQueueDemo {

    public static Queue<Double> getNaturalModOrderedQueue() {
        return new PriorityQueue<>((n1, n2) -> Double.compare(Math.abs(n1), Math.abs(n2)));
    }

    public static double calcAverage(final Queue<Double> nums) {
        return nums.stream().mapToDouble(d -> d).average().orElse(0);
    }

    private static void printInfo(final Queue<Double> nums) {
        Queue<Double> queue = getNaturalModOrderedQueue();
        queue.addAll(nums);
        nums.forEach(e -> System.out.println(queue.poll()));
        System.out.println("Number of elements: "+nums.size());
        System.out.printf("Average of all elements: %.2f%n%n", calcAverage(nums));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        Queue<Double> queue = getNaturalModOrderedQueue();
        boolean exit = false;
        while(!exit){
            System.out.println("Enter number(s) to add to queue or character(s) to exit:");
            if(sc.hasNextDouble()){
                queue.add(sc.nextDouble());
                printInfo(queue);
            } else {
                exit = true;
            }
        }
    }
}
