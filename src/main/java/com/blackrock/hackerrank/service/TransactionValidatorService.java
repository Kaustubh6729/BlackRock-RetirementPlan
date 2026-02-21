package com.blackrock.hackerrank.service;

import com.blackrock.hackerrank.model.*;
import com.blackrock.hackerrank.util.MoneyUtil;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionValidatorService {

    public ValidationResponse validate(
            ValidationRequest request) {

        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();

        Set<LocalDateTime> seenDates = new HashSet<>();

        for (Transaction tx : request.getTransactions()) {

            String error = validateSingle(tx, seenDates);

            if (error == null) {

                valid.add(tx);

                seenDates.add(tx.getDate());

            } else {

                invalid.add(new InvalidTransaction(tx, error));
            }
        }

        return new ValidationResponse(valid, invalid);
    }

    private String validateSingle(
            Transaction tx,
            Set<LocalDateTime> seenDates) {

        if (tx == null) {
            return "Transaction is null";
        }

        if (tx.getDate() == null) {
            return "Missing timestamp";
        }

        if (seenDates.contains(tx.getDate())) {
            return "Duplicate transaction timestamp";
        }

        if (tx.getAmount() < 0) {
            return "Negative amount not allowed";
        }

        double expectedCeiling =
                MoneyUtil.calculateCeiling(
                        tx.getAmount()
                );

        if (tx.getCeiling() != expectedCeiling) {
            return "Invalid ceiling value";
        }

        double expectedRemanent =
                expectedCeiling - tx.getAmount();

        if (tx.getRemanent() != expectedRemanent) {
            return "Invalid remanent value";
        }

        return null;
    }
}