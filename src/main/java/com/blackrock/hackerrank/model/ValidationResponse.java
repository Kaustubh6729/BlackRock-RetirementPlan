package com.blackrock.hackerrank.model;

import java.util.List;

public class ValidationResponse {

    private List<Transaction> valid;
    private List<InvalidTransaction> invalid;

    public ValidationResponse(
            List<Transaction> valid,
            List<InvalidTransaction> invalid) {

        this.valid = valid;
        this.invalid = invalid;
    }

    public List<Transaction> getValid() {
        return valid;
    }

    public List<InvalidTransaction> getInvalid() {
        return invalid;
    }
}