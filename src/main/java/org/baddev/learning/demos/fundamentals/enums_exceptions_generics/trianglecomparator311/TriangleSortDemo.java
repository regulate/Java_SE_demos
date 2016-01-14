package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.trianglecomparator311;

import java.util.Arrays;

public class TriangleSortDemo {

    public static void main(String[] args) {
        Triangle [] trs = new Triangle[4];
        trs[0] = new Triangle(3.2,4.15,90);
        trs[1] = new Triangle(5.7,1.15,25);
        trs[2] = new Triangle(13.12,0.29,67);
        trs[3] = new Triangle(1.45,4.37,29);

        System.out.println("Unsorted: "+Arrays.toString(trs));
        Arrays.sort(trs, (a,b) -> Double.compare(a.calcArea(), b.calcArea()));
        System.out.println("Sorted: "+Arrays.toString(trs));
    }

}
