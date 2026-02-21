package com.blackrock.hackerrank.model;

import java.util.List;

public class FilterRequest {

    private List<Period> q;
    private List<Period> p;
    private List<Period> k;

    private List<Transaction> transactions;

    public FilterRequest() {
    }

    public List<Period> getQ() {
        return q;
    }

    public void setQ(List<Period> q) {
        this.q = q;
    }

    public List<Period> getP() {
        return p;
    }

    public void setP(List<Period> p) {
        this.p = p;
    }

    public List<Period> getK() {
        return k;
    }

    public void setK(List<Period> k) {
        this.k = k;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}