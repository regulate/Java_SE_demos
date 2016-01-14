package org.baddev.learning.demos.fundamentals.syntax_constructions.pi313;

public class CalculatingPiDemo {

    private static void checkEpsilonArg(final double epsilon){
        if(epsilon <= 0){
            throw new IllegalArgumentException("Epsilon must be greater than 0");
        }
    }

    public static double calcPi(final double epsilon) {
        checkEpsilonArg(epsilon);
        double result = 3;
        boolean oddStep = true;
        for (double i = 2, summand = 0; epsilon <= (summand = 4d / (i * (i + 1d) * (i + 2d))); i += 2) {
            if (oddStep) {
                result += summand;
            } else {
                result -= summand;
            }
            oddStep = !oddStep;
        }
        return result;
    }

    public static double calcPiRecursively(final double epsilon) {
        checkEpsilonArg(epsilon);
        return calcPiSummand(epsilon, 2, 3);
    }

    private static double calcPiSummand(final double epsilon, final int i, double pi) {
        double summand = 4d / (i * (i + 1d) * (i + 2d));
        if (summand < epsilon) {
            return pi;
        } else {
            return ((i / 2) % 2 == 1) ?
                    calcPiSummand(epsilon, i + 2, pi += summand) : calcPiSummand(epsilon, i + 2, pi -= summand);
        }
    }

    public static void main(String[] args) {
        System.out.println("Using loop: "+calcPi(0.0001));
        System.out.println("Using recursion: "+calcPiRecursively(0.0001));
    }

}
