package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.trianglecomparator311;

public class Triangle {

    private double a;
    private double b;
    private int angle;
    private double c;

    public Triangle(double aSideLength, double bSideLength, int angle) {
        this.a = aSideLength;
        this.b = bSideLength;
        this.angle = angle;
        calcCSideLength();
    }

    public double getASideLength() {
        return a;
    }

    public double getBSideLength() {
        return b;
    }

    public int getAngle() {
        return angle;
    }

    public double getCSideLength() {
        return c;
    }

    public double calcArea() {
        double result = (1d / 4d) * (Math.sqrt((a + b + c) * (b + c - a) * (a + c - b) * (a + b - c)));
        return result;
    }

    private void calcCSideLength(){
        double result = Math.pow(a, 2) + Math.pow(b, 2) - 2*a*b*Math.cos(convertDegToRad(angle));
        c = Math.sqrt(result);
    }

    private double convertDegToRad(int angle){
        return angle*(Math.PI/180);
    }

    @Override
    public String toString() {
        return String.format("Area: %.2f", calcArea());
    }
}
