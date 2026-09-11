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
    // [GET] 특정 거래 내역 단건 조회 (예: /api/transactions/1)
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Long id) {
        return transactionService.getTransaction(id);
    }

    // [PUT] 거래 내역 수정
    @PutMapping("/{id}")
    public String updateTransaction(@PathVariable("id") Long id, @RequestBody TransactionRequestDto dto) {
        Long updatedId = transactionService.updateTransaction(id, dto);
        return "수정 완료! 거래 내역 ID: " + updatedId;
    }

    // [DELETE] 거래 내역 삭제
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return "삭제 완료! 대상 ID: " + id;
    }
}