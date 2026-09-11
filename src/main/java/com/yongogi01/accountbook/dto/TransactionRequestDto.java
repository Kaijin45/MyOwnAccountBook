package com.yongogi01.accountbook.dto;

import com.yongogi01.accountbook.entity.TransactionType;
import java.time.LocalDateTime;

public class TransactionRequestDto {
    private TransactionType type;
    private Long amount;
    private String category;
    private String memo;
    private LocalDateTime transactionDate;

    public TransactionRequestDto() {}

    public TransactionType getType() { return type; }
    public Long getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getMemo() { return memo; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
}