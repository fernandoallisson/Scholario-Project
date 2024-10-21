package com.scholario.scholario_demo.dto.payment;

import com.scholario.scholario_demo.entiity.Payment;

public record PaymentDto(Long id, Long studentId, String name, String paymentMethod,
                         String paymentDate, Double paymentValue, String paymentStatus,
                         String paymentType, String paymentDescription) {

  public static PaymentDto fromEntity(Payment payment) {
    return new PaymentDto(
        payment.getId(),
        payment.getStudentPayments().getId(),
        payment.getStudentPayments().getName(),
        payment.getPaymentMethod(),
        payment.getPaymentDate(),
        payment.getPaymentValue(),
        payment.getPaymentStatus(),
        payment.getPaymentType(),
        payment.getPaymentDescription());
  }

}
