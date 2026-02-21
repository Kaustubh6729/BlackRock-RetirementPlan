package com.blackrock.hackerrank.service;

import com.blackrock.hackerrank.model.*;
import com.blackrock.hackerrank.util.PeriodUtil;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionFilterService {

    public FilterResponse filter(FilterRequest request) {

        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();

        if (request.getTransactions() == null) {
            return new FilterResponse(valid, invalid);
        }

        for (Transaction tx : request.getTransactions()) {

            try {

                Transaction updated =
                        applyRules(
                                tx,
                                request.getQ(),
                                request.getP()
                        );

                if (belongsToAnyKPeriod(
                        updated,
                        request.getK()
                )) {

                    valid.add(updated);

                } else {

                    invalid.add(
                            new InvalidTransaction(
                                    tx,
                                    "Outside k period range"
                            )
                    );
                }

            } catch (Exception ex) {

                invalid.add(
                        new InvalidTransaction(
                                tx,
                                ex.getMessage()
                        )
                );
            }
        }

        return new FilterResponse(valid, invalid);
    }

    private Transaction applyRules(
            Transaction tx,
            List<Period> q,
            List<Period> p) {

        LocalDateTime date = tx.getDate();

        Period fixed =
                PeriodUtil.findApplicableFixedPeriod(
                        date,
                        q
                );

        double remanent =
                fixed != null
                        ? fixed.getFixed()
                        : tx.getRemanent();

        remanent += PeriodUtil.calculateTotalExtras(
                date,
                p
        );

        return new Transaction(
                tx.getDate(),
                tx.getAmount(),
                tx.getCeiling(),
                remanent
        );
    }

    private boolean belongsToAnyKPeriod(
            Transaction tx,
            List<Period> kPeriods) {

        if (kPeriods == null || kPeriods.isEmpty()) {
            return true;
        }

        for (Period period : kPeriods) {

            if (PeriodUtil.isWithinPeriod(
                    tx.getDate(),
                    period
            )) {
                return true;
            }
        }

        return false;
    }
}