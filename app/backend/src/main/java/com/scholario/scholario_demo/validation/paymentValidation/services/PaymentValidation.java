package com.scholario.scholario_demo.validation.paymentValidation.services;

import com.scholario.scholario_demo.entiity.Payment;
import com.scholario.scholario_demo.validation.paymentValidation.exception.PaymentIntegrityException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PaymentValidation {

  private static final List<String> paymentMethods = List.of("credito", "debito", "pix", "boleto", "dinheiro", "cheque",
      "outro");
  private static final List<String> paymentStatus = List.of("pendente", "confirmado", "cancelado");
  private static final List<String> paymentTypes = List.of("mensalidade", "matricula", "material", "outro");
  private static final String DATE_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

  public void validatePayment(Payment payment) {
    validatePaymentValue(payment.getPaymentValue());
    validatePaymentMethod(payment.getPaymentMethod());
    validatePaymentStatus(payment.getPaymentStatus());
    validatePaymentType(payment.getPaymentType());
    validatePaymentDate(payment.getPaymentDate());
  }

  private void validatePaymentValue(Double value) {
    if (value == null) {
      throw new IllegalArgumentException("Valor do pagamento não pode ser vazio");
    }

    if (value < 0) {
      throw new PaymentIntegrityException("Valor do pagamento não pode ser negativo");
    }

    if (value > 1000000) {
      throw new PaymentIntegrityException("Valor do pagamento não pode ser maior que 1.000.000");
    }

    // Para ver se contém apenas números e ponto
    if (!String.valueOf(value).matches("^[0-9]*\\.?[0-9]*$")) {
      throw new PaymentIntegrityException("Valor do pagamento deve conter apenas números e ponto");

    }
  }

  private void validatePaymentMethod(String method) {
    if (method == null || method.isEmpty()) {
      throw new PaymentIntegrityException("Método de pagamento não pode ser vazio");
    }

    if (!paymentMethods.contains(method)) {
      throw new PaymentIntegrityException("Método de pagamento inválido");
    }
  }

  private void validatePaymentStatus(String status) {
    if (status == null || status.isEmpty()) {
      throw new PaymentIntegrityException("Status do pagamento não pode ser vazio");
    }

    if (!paymentStatus.contains(status)) {
      throw new PaymentIntegrityException("Status do pagamento inválido");
    }
  }

  private void validatePaymentType(String type) {
    if (type == null || type.isEmpty()) {
      throw new PaymentIntegrityException("Tipo de pagamento não pode ser vazio");
    }

    if (!paymentTypes.contains(type)) {
      throw new PaymentIntegrityException("Tipo de pagamento inválido");
    }
  }

  public void validatePaymentDate(String date) {
    if (date == null || date.isEmpty()) {
      throw new PaymentIntegrityException("Data do pagamento não pode ser vazia");
    }

    if (!date.matches(DATE_REGEX)) {
      throw new PaymentIntegrityException("Data do pagamento inválida");
    }
  }
}
