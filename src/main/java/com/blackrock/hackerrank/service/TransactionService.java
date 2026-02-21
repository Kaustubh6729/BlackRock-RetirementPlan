package com.blackrock.hackerrank.service;

import com.blackrock.hackerrank.model.Expense;
import com.blackrock.hackerrank.model.Transaction;
import com.blackrock.hackerrank.util.MoneyUtil;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public List<Transaction> parseExpenses(List<Expense> expenses) {

        if (expenses == null || expenses.isEmpty()) {
            return List.of();
        }

        List<Transaction> transactions =
                new ArrayList<>(expenses.size());

        for (Expense expense : expenses) {

            if (expense == null) {
                continue;
            }

            double amount = expense.getAmount();

            if (amount < 0) {
                continue;
            }

            double ceiling =
                    MoneyUtil.calculateCeiling(amount);

            double remanent =
                    ceiling - amount;

            Transaction tx = new Transaction(
                    expense.getDate(),
                    amount,
                    ceiling,
                    remanent
            );

            transactions.add(tx);
        }

        return transactions;
    }

    public double calculateTotalAmount(
            List<Transaction> transactions) {

        double total = 0;

        for (Transaction tx : transactions) {
            total += tx.getAmount();
        }

        return total;
    }

    public double calculateTotalCeiling(
            List<Transaction> transactions) {

        double total = 0;

        for (Transaction tx : transactions) {
            total += tx.getCeiling();
        }

        return total;
    }
}