package org.baddev.learning.demos.fundamentals.classes_inh_polym.functionmin310;

public class FunctionIntervalDemo {

    public static void main(String[] args) {
        FunctionInterval sin = new SinInterval();
        sin.setInterval(-7.3d, 7.3d, 0.1d);
        sin.printMinPoint();
        System.out.println("");
        sin.printIntervalValues();

        FunctionInterval cos = new CosInterval();
        cos.setInterval(-2d, 2d, 0.1d);
        System.out.println("");
        cos.printMinPoint();
        System.out.println("");
        cos.printIntervalValues();

        FunctionInterval sqr = new SquareInterval();
        sqr.setInterval(2d, 3d, 0.1d);
        System.out.println("");
        sqr.printMinPoint();
        System.out.println("");
        sqr.printIntervalValues();
    }

}