package org.baddev.learning.demos.fundamentals.syntax_constructions.binaryrevertedrepr36;

public class BinaryRevertedReprDemo {

    public static String toBinaryStringWithRevertedOrder(final byte value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 7; i++) {
            sb.append((byte)(1&(value>>i)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        byte b = 5;
        System.out.println(toBinaryStringWithRevertedOrder(b));
    }

}
