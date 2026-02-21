package com.blackrock.hackerrank.model;

import java.util.List;

public class ReturnResponse {

    private double transactionsTotalAmount;
    private double transactionsTotalCeiling;

    private List<SavingResult> savingsByDates;

    public ReturnResponse(double totalAmount,
                          double totalCeiling,
                          List<SavingResult> savings) {

        this.transactionsTotalAmount = totalAmount;
        this.transactionsTotalCeiling = totalCeiling;
        this.savingsByDates = savings;
    }

    public double getTransactionsTotalAmount() {
        return transactionsTotalAmount;
    }

    public double getTransactionsTotalCeiling() {
        return transactionsTotalCeiling;
    }

    public List<SavingResult> getSavingsByDates() {
        return savingsByDates;
    }
}