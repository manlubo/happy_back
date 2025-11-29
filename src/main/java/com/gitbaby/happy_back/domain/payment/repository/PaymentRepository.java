package com.gitbaby.happy_back.domain.payment.repository;

import com.gitbaby.happy_back.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
