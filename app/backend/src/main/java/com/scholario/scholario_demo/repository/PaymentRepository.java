package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
