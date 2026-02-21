package com.blackrock.hackerrank.util;

public final class TaxUtil {

    private static final double MAX_NPS_LIMIT = 200000;

    private TaxUtil() {
    }

    public static double calculateTax(double income) {

        if (income <= 700000) {
            return 0;
        }

        if (income <= 1000000) {
            return (income - 700000) * 0.10;
        }

        if (income <= 1200000) {
            return 30000 +
                    (income - 1000000) * 0.15;
        }

        if (income <= 1500000) {
            return 60000 +
                    (income - 1200000) * 0.20;
        }

        return 120000 +
                (income - 1500000) * 0.30;
    }

    public static double calculateNpsDeduction(
            double invested,
            double annualIncome) {

        double tenPercent = annualIncome * 0.10;

        return Math.min(
                invested,
                Math.min(tenPercent, MAX_NPS_LIMIT)
        );
    }

    public static double calculateTaxBenefit(
            double annualIncome,
            double investedAmount) {

        double deduction =
                calculateNpsDeduction(
                        investedAmount,
                        annualIncome
                );

        double originalTax =
                calculateTax(annualIncome);

        double reducedTax =
                calculateTax(
                        annualIncome - deduction
                );

        return originalTax - reducedTax;
    }
}