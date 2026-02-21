package com.blackrock.hackerrank.util;

public final class MoneyUtil {

    private static final int ROUND_BASE = 100;

    private MoneyUtil() {    }

    public static double calculateCeiling(double amount) {

        if (amount <= 0) {
            return 0;
        }

        long multiplier = (long) Math.ceil(amount / ROUND_BASE);

        return multiplier * ROUND_BASE;
    }

    public static double calculateRemanent(double amount) {

        double ceiling = calculateCeiling(amount);

        return ceiling - amount;
    }

    public static double safeAdd(double a, double b) {

        double result = a + b;

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new ArithmeticException("Invalid monetary addition");
        }

        return result;
    }
}