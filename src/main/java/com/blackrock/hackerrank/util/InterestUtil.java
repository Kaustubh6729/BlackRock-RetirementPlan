package com.blackrock.hackerrank.util;

public final class InterestUtil {

    private InterestUtil() {
    }

    public static double compoundInterest(
            double principal,
            double annualRate,
            int years) {

        if (principal <= 0 || years <= 0) {
            return principal;
        }

        return principal *
                Math.pow(1.0 + annualRate, years);
    }

    public static double adjustForInflation(
            double futureValue,
            double inflationRate,
            int years) {

        if (futureValue <= 0 || inflationRate <= 0) {
            return futureValue;
        }

        return futureValue /
                Math.pow(1.0 + inflationRate, years);
    }
}