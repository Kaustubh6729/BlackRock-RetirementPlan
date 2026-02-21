package com.blackrock.hackerrank.model;

import java.util.List;

public class ReturnRequest {

    private int age;
    private double wage;
    private double inflation;

    private List<Period> q;
    private List<Period> p;
    private List<Period> k;

    private List<Transaction> transactions;

    public int getAge() {
        return age;
    }

    public double getWage() {
        return wage;
    }

    public double getInflation() {
        return inflation;
    }

    public List<Period> getQ() {
        return q;
    }

    public List<Period> getP() {
        return p;
    }

    public List<Period> getK() {
        return k;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}