package org.baddev.learning.demos.fundamentals.syntax_constructions.binarywithoutzeroes35;

public class BinaryReprWithoutLeadZeroesDemo {

    public static String toBinaryStringWithoutLeadingZrs(final byte value) {
        StringBuilder sb = new StringBuilder();
        byte b = 0;
        boolean leadingZero = false;
        for (int i = 7; i >= 0; i--) {
            b = (byte) (1 & (value >> i));
            /*
            If first iteration had found leading 0, and then it found neighbour 0, will go to next iteration.
            And if it founds 1, all values placed after it will be added to result.
            */
            if (b == 0 && leadingZero) {
                continue;
            } else if (i == 7 && b == 0) {
                leadingZero = true;
            } else {
                sb.append(b);
                leadingZero = false;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        byte val = 25;
        System.out.println(toBinaryStringWithoutLeadingZrs(val));
    }

}
