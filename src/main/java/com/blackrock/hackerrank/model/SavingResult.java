package com.blackrock.hackerrank.model;

import java.time.LocalDateTime;

public class SavingResult {

    private LocalDateTime start;
    private LocalDateTime end;
    private double amount;

    public SavingResult() {
    }

    public SavingResult(LocalDateTime start,
                        LocalDateTime end,
                        double amount) {

        this.start = start;
        this.end = end;
        this.amount = amount;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}