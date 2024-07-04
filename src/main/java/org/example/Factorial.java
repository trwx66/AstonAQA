package org.example;

import java.math.BigInteger;

public class Factorial {

    public BigInteger getFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(getFactorial(n-1));
    }
}
