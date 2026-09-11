package com.yongogi01.accountbook.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionType type; // 수입 or 지출

    @Column(nullable = false)
    private Long amount; // 금액 (금융 도메인 특성상 부동소수점 오차 방지를 위해 Long/BigDecimal 권장)

    @Column(nullable = false, length = 50)
    private String category; // 예: 식비, 교통, 월급 등

    @Column(length = 255)
    private String memo; // 메모/상세 내용

    @Column(nullable = false)
    private LocalDateTime transactionDate; // 결제/거래 일시

    protected Transaction() {}

    public Transaction(TransactionType type, Long amount, String category, String memo, LocalDateTime transactionDate) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.memo = memo;
        this.transactionDate = transactionDate;
    }

    // Getter 메서드들
    public Long getId() { return id; }
    public TransactionType getType() { return type; }
    public Long getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getMemo() { return memo; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
}