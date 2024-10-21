package com.scholario.scholario_demo.dto.payment;

import com.scholario.scholario_demo.entiity.Payment;

public record PaymentCreationDto( String paymentMethod,
                                 String paymentDate, Double paymentValue, String paymentStatus,
                                 String paymentType, String paymentDescription
) {

  public Payment toEntity() {
    return new Payment(
        this.paymentMethod(),
        this.paymentDate(),
        this.paymentValue(),
        this.paymentStatus(),
        this.paymentType(),
        this.paymentDescription()
    );
  }

}
