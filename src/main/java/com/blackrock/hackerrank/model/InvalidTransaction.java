package com.blackrock.hackerrank.model;

public class InvalidTransaction extends Transaction {

    private String message;

    public InvalidTransaction(Transaction tx, String message) {

        super(
            tx.getDate(),
            tx.getAmount(),
            tx.getCeiling(),
            tx.getRemanent()
        );

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}