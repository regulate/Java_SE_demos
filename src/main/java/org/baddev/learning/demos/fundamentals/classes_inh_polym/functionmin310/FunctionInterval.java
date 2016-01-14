package org.baddev.learning.demos.fundamentals.classes_inh_polym.functionmin310;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class FunctionInterval {

    private double lowerBound = 0;
    private double upperBound = 1;
    private double step = 0.01;
    private Map<Double, Double> intervalVals = new LinkedHashMap<>();

    public FunctionInterval() {
    }

    /**
     * @see FunctionInterval#setInterval(double, double, double)
     */
    public FunctionInterval(double lowerBound, double upperBound, double step) {
        setInterval(lowerBound, upperBound, step);
    }

    /**
     * Initializes an instance. Also calculates all values of the function on the specified interval
     *
     * @param lowerBound bottom value of the numbers interval
     * @param upperBound upper value of the numbers interval
     * @param step       to move over interval
     */
    public void setInterval(double lowerBound, double upperBound, double step) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("lowerBound param value must be lt upperBound param value");
        } else if (step > Math.abs(upperBound - lowerBound) || step <= 0) {
            throw new IllegalArgumentException("Incorrect value of parameter step: " + step);
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
        calcFunctionVals();
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getStep() {
        return step;
    }

    public Map<Double, Double> getIntervalValues() {
        return intervalVals;
    }

    public List<Double> getIntervalPoints() {
        return intervalVals.keySet().stream().collect(Collectors.toList());
    }

    public List<Double> getFunctionValues() {
        return intervalVals.values().stream().collect(Collectors.toList());
    }

    private void calcFunctionVals() {
        for (double i = lowerBound; i <= upperBound; i += step) {
            double val = function(i);
            intervalVals.put(i, val);
        }
    }

    public void printIntervalValues() {
        intervalVals.forEach((key, val) -> {
            System.out.printf("Point value: %f, function value: %.4f%n", key, val);
        });
    }

    public double findMinPoint() {
        double minVal = Collections.min(intervalVals.values());
        return intervalVals.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(minVal))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .get(0);
    }

    public void printMinPoint(){
        double minPoint = findMinPoint();
        System.out.printf("Min value of function: %.4f in point %f%n", intervalVals.get(minPoint), minPoint);
    }

    protected abstract double function(double x);

}

class SinInterval extends FunctionInterval {
    /**
     * @param x value in radians
     * @return sin of {@code x}
     */
    @Override
    protected double function(double x) {
        return Math.sin(x);
    }
}

class CosInterval extends FunctionInterval {
    /**
     * @param x value in radians
     * @return sin of {@code x}
     */
    @Override
    protected double function(double x) {
        return Math.cos(x);
    }
}

class SquareInterval extends FunctionInterval {

    @Override
    protected double function(double x) {
        if (x >= Math.sqrt(Double.MAX_VALUE)) {
            throw new IllegalArgumentException("Value of x is too big");
        }
        return x * x;
    }
}
