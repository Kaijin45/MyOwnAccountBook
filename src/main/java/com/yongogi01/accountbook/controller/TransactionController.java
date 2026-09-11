package com.yongogi01.accountbook.controller;

import com.yongogi01.accountbook.dto.TransactionRequestDto;
import com.yongogi01.accountbook.entity.Transaction;
import com.yongogi01.accountbook.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // [POST] 거래 내역 등록 API
    @PostMapping
    public String createTransaction(@RequestBody TransactionRequestDto dto) {
        Long savedId = transactionService.saveTransaction(dto);
        return "등록 완료! 거래 내역 ID: " + savedId;
    }

    // [GET] 전체 거래 내역 조회 API
    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }
}