package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.fourthroot34;

public class FourthRoot {

    private static class RootOfNegativeException extends Exception{

        private Number value;
        private static final String message = "Found negative value of odd root argument: ";

        public RootOfNegativeException(double value) {
            super(message+value);
            this.value = value;
        }

        public Number getValue() {
            return value;
        }
    }

    /**
     * @param num number
     * @return fourth root of {@code num}
     * @throws RootOfNegativeException if {@code num &lt 0}
     */
    public static double findFourthRoot(double num) throws RootOfNegativeException{
        if(num<0){
            throw new RootOfNegativeException(num);
        }
        return Math.sqrt(Math.sqrt(num));
    }

    public static void main(String[] args) {
        double res = 0;
        try {
            res = FourthRoot.findFourthRoot(2);
            System.out.println(res);
            res = FourthRoot.findFourthRoot(-2);
        } catch (RootOfNegativeException e){
            e.printStackTrace();
        }
    }

}
