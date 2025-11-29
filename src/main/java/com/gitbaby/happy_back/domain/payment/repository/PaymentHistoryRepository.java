package com.gitbaby.happy_back.domain.payment.repository;

import com.gitbaby.happy_back.domain.payment.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
