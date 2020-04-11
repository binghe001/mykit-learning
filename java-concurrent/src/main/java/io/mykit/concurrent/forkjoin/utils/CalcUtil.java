package io.mykit.concurrent.forkjoin.utils;

import java.math.BigInteger;

public class CalcUtil {

    public static BigInteger calculateFactorial (BigInteger input) {
        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE;
             i.compareTo(input) <= 0;
             i = i.add(BigInteger.ONE)) {
            factorial = factorial.multiply(i);
        }
        return factorial;
    }
}