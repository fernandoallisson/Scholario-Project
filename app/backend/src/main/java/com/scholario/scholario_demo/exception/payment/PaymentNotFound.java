package com.scholario.scholario_demo.exception.payment;

public class PaymentNotFound extends RuntimeException {

  public PaymentNotFound(String message) {
    super(message);
  }
}
