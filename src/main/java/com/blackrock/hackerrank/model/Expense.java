package com.blackrock.hackerrank.model;

import java.time.LocalDateTime;

public class Expense {

    private LocalDateTime date;
    private double amount;

    public Expense() {
    }

    public Expense(LocalDateTime date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}