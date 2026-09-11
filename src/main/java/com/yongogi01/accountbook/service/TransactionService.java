package com.yongogi01.accountbook.service;

import com.yongogi01.accountbook.dto.TransactionRequestDto;
import com.yongogi01.accountbook.entity.Transaction;
import com.yongogi01.accountbook.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    // 생성자 주입: 스프링이 만들어둔 금고 관리자(Repository)를 이 서비스에 꽂아줌 (DI: 의존성 주입)
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // 1. 거래 내역 저장하기
    @Transactional
    public Long saveTransaction(TransactionRequestDto dto) {
        // DTO(신청서)의 알맹이를 꺼내서 DB 저장용 Entity로 조립
        Transaction transaction = new Transaction(
                dto.getType(),
                dto.getAmount(),
                dto.getCategory(),
                dto.getMemo(),
                dto.getTransactionDate()
        );
        // DB에 저장하고, 자동으로 발급된 ID(고유 번호)를 반환
        Transaction saved = transactionRepository.save(transaction);
        return saved.getId();
    }

    // 2. 전체 거래 내역 조회하기
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}