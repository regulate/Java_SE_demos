package org.baddev.learning.demos.se_basics.se_basic_comp.usingbiginteger31;

import java.math.BigInteger;
import java.util.Random;

public class PowerCalculating {

    public static BigInteger random(BigInteger max) {
        Random rand = new Random();
        while (true) {
            BigInteger i = new BigInteger(max.bitLength(), rand);
            if (i.compareTo(max) <= 0)
                return i;
        }
    }

    public static BigInteger customPow(BigInteger val, final int power) {
        BigInteger result = new BigInteger(val.toString());
        for (int i = 2; i <= power; i++) {
            result = result.multiply(val);
        }
        return (power == 0) ? BigInteger.ONE : (power == 1) ? val : result;
    }

    public static void main(String[] args) {
        BigInteger num = random(new BigInteger("1232146214871264512961"));
        System.out.println(num);
        System.out.println(customPow(num, 15));
        System.out.println(num.pow(15));
    }

}
