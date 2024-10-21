package com.scholario.scholario_demo.globalAdvice;

import com.scholario.scholario_demo.validation.paymentValidation.exception.PaymentIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scholario.scholario_demo.validation.userValidation.exception.DataIntegrityException;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Ocorreu um erro interno, por favor tente novamente.");
  }

  /**
   * Handle your specific exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleYourSpecificException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

  @ExceptionHandler(DataIntegrityException.class)
  public ResponseEntity<String> handleDataIntegrityException(DataIntegrityException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

  @ExceptionHandler(PaymentIntegrityException.class)
  public ResponseEntity<String> handlePaymentIntegrityException(PaymentIntegrityException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }
}