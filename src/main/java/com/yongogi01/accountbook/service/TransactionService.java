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

    // 3. 단건 상세 조회
    @Transactional(readOnly = true)
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 거래 내역이 존재하지 않습니다. ID = " + id));
    }

    // 4. 거래 내역 수정 (더티 체킹 활용)
    @Transactional
    public Long updateTransaction(Long id, TransactionRequestDto dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 거래 내역이 존재하지 않습니다. ID = " + id));

        // JPA의 영속성 컨텍스트(Dirty Checking): 엔티티 객체의 값만 바꾸면 트랜잭션 종료 시점에 자동으로 DB에 UPDATE 쿼리를 날려줌
        transaction.update(
                dto.getType(),
                dto.getAmount(),
                dto.getCategory(),
                dto.getMemo(),
                dto.getTransactionDate()
        );

        return transaction.getId();
    }

    // 5. 거래 내역 삭제
    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 거래 내역이 존재하지 않습니다. ID = " + id));

        transactionRepository.delete(transaction);
    }
}