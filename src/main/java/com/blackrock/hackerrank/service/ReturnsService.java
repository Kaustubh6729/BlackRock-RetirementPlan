package com.blackrock.hackerrank.service;

import com.blackrock.hackerrank.model.*;
import com.blackrock.hackerrank.util.InterestUtil;
import com.blackrock.hackerrank.util.PeriodUtil;
import com.blackrock.hackerrank.util.TaxUtil;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReturnsService {

    private static final double NPS_RATE = 0.0711;
    private static final double INDEX_RATE = 0.1449;

    public ReturnResponse calculateNps(ReturnRequest request) {

        List<Transaction> updated =
                applyTemporalRules(
                        request.getTransactions(),
                        request.getQ(),
                        request.getP()
                );

        TreeMap<LocalDateTime, Double> prefix =
                buildPrefixSum(updated);

        List<SavingResult> savings =
                calculateSavings(
                        prefix,
                        request.getK()
                );

        int years = calculateYears(request.getAge());
        double annualIncome = request.getWage() * 12;

        for (SavingResult s : savings) {

            double future =
                    InterestUtil.compoundInterest(
                            s.getAmount(),
                            NPS_RATE,
                            years
                    );

            double adjusted =
                    InterestUtil.adjustForInflation(
                            future,
                            request.getInflation(),
                            years
                    );

            double taxBenefit =
                    TaxUtil.calculateTaxBenefit(
                            annualIncome,
                            s.getAmount()
                    );

            s.setAmount(adjusted + taxBenefit);
        }

        return new ReturnResponse(
                sumAmount(updated),
                sumCeiling(updated),
                savings
        );
    }

    public ReturnResponse calculateIndex(ReturnRequest request) {

        List<Transaction> updated =
                applyTemporalRules(
                        request.getTransactions(),
                        request.getQ(),
                        request.getP()
                );

        TreeMap<LocalDateTime, Double> prefix =
                buildPrefixSum(updated);

        List<SavingResult> savings =
                calculateSavings(
                        prefix,
                        request.getK()
                );

        int years = calculateYears(request.getAge());

        for (SavingResult s : savings) {

            double future =
                    InterestUtil.compoundInterest(
                            s.getAmount(),
                            INDEX_RATE,
                            years
                    );

            double adjusted =
                    InterestUtil.adjustForInflation(
                            future,
                            request.getInflation(),
                            years
                    );

            s.setAmount(adjusted);
        }

        return new ReturnResponse(
                sumAmount(updated),
                sumCeiling(updated),
                savings
        );
    }

    private List<Transaction> applyTemporalRules(
            List<Transaction> transactions,
            List<Period> q,
            List<Period> p) {

        List<Transaction> result =
                new ArrayList<>(transactions.size());

        for (Transaction tx : transactions) {

            LocalDateTime date = tx.getDate();

            Period fixed =
                    PeriodUtil.findApplicableFixedPeriod(
                            date, q
                    );

            double rem =
                    fixed != null
                            ? fixed.getFixed()
                            : tx.getRemanent();

            rem += PeriodUtil.calculateTotalExtras(
                    date, p
            );

            result.add(
                    new Transaction(
                            tx.getDate(),
                            tx.getAmount(),
                            tx.getCeiling(),
                            rem
                    )
            );
        }

        result.sort(
                Comparator.comparing(Transaction::getDate)
        );

        return result;
    }

    private TreeMap<LocalDateTime, Double> buildPrefixSum(
            List<Transaction> transactions) {

        TreeMap<LocalDateTime, Double> prefix =
                new TreeMap<>();

        double running = 0;

        for (Transaction tx : transactions) {

            running += tx.getRemanent();

            prefix.put(tx.getDate(), running);
        }

        return prefix;
    }

    private List<SavingResult> calculateSavings(
            TreeMap<LocalDateTime, Double> prefix,
            List<Period> periods) {

        List<SavingResult> output =
                new ArrayList<>();

        for (Period p : periods) {

            double sum =
                    queryRange(prefix,
                            p.getStart(),
                            p.getEnd());

            output.add(
                    new SavingResult(
                            p.getStart(),
                            p.getEnd(),
                            sum
                    )
            );
        }

        return output;
    }

    private double queryRange(
            TreeMap<LocalDateTime, Double> prefix,
            LocalDateTime start,
            LocalDateTime end) {

        Map.Entry<LocalDateTime, Double> endEntry =
                prefix.floorEntry(end);

        if (endEntry == null)
            return 0;

        Map.Entry<LocalDateTime, Double> beforeStart =
                prefix.lowerEntry(start);

        if (beforeStart == null)
            return endEntry.getValue();

        return endEntry.getValue()
                - beforeStart.getValue();
    }

    private int calculateYears(int age) {

        return age >= 60 ? 5 : 60 - age;
    }

    private double sumAmount(List<Transaction> tx) {

        double sum = 0;

        for (Transaction t : tx)
            sum += t.getAmount();

        return sum;
    }

    private double sumCeiling(List<Transaction> tx) {

        double sum = 0;

        for (Transaction t : tx)
            sum += t.getCeiling();

        return sum;
    }
}