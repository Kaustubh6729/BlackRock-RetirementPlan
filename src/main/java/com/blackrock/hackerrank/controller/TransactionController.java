package com.blackrock.hackerrank.controller;

import com.blackrock.hackerrank.model.*;
import com.blackrock.hackerrank.service.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionValidatorService validatorService;
    private final TransactionFilterService filterService;

    public TransactionController(
            TransactionService transactionService,
            TransactionValidatorService validatorService,
            TransactionFilterService filterService) {

        this.transactionService = transactionService;
        this.validatorService = validatorService;
        this.filterService = filterService;
    }

    @PostMapping("/transactions:parse")
    public List<Transaction> parseTransactions(
            @RequestBody List<Expense> expenses) {

        return transactionService.parseExpenses(expenses);
    }

    @PostMapping("/transactions:validator")
    public ValidationResponse validateTransactions(
            @RequestBody ValidationRequest request) {

        return validatorService.validate(request);
    }
    @PostMapping("/transactions:filter")
    public FilterResponse filterTransactions(
            @RequestBody FilterRequest request) {

        return filterService.filter(request);
    }
}