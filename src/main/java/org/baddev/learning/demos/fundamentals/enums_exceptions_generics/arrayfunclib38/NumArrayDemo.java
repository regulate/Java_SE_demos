package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.arrayfunclib38;

public class NumArrayDemo {

    public static void main(String[] args) {
        Double [] doubles = {1.2,3.3,2.1,0d,-4.5,-11.6,0d,3.33,-7.11,11d,4.35};
        Integer [] integers = {2,4,0,-5,6,1,0,-2,0,11,-16,1, -81, 94};

        System.out.printf("Count of negative elements(doubles): %d%n", NumArrayUtils.countNegatives(doubles));
        System.out.printf("Count of negative elements(integers): %d%n", NumArrayUtils.countNegatives(integers));

        System.out.printf("First zero index(doubles): %d%n", NumArrayUtils.findFirstZeroIdx(doubles));
        System.out.printf("First zero index(integers): %d%n", NumArrayUtils.findFirstZeroIdx(integers));

        System.out.printf("Last negative element(doubles): %.2f%n", NumArrayUtils.findLastNegative(doubles));
        System.out.printf("Last negative element(integers): %d%n", NumArrayUtils.findLastNegative(integers));
    }

}
