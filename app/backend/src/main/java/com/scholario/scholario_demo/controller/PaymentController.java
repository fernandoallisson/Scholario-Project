package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.entiity.Payment;
import com.scholario.scholario_demo.exception.payment.PaymentNotFound;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.scholario.scholario_demo.dto.payment.PaymentCreationDto;
import com.scholario.scholario_demo.dto.payment.PaymentDto;
import com.scholario.scholario_demo.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/{StudentId}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> createPayment(@PathVariable Long StudentId,
      @RequestBody PaymentCreationDto paymentCreationDto) {
    return ResponseEntity.ok(
        PaymentDto.fromEntity(
            paymentService.createPayment(StudentId, paymentCreationDto.toEntity())));
  }

  @GetMapping
  public ResponseEntity<?> getPayments(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {

    List<Payment> payments = paymentService.getAllPayments(pageNumber, pageSize);

    return ResponseEntity.ok(
        payments.stream()
            .map(PaymentDto::fromEntity)
            .toList()
    );

  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(PaymentDto.fromEntity(paymentService.getPaymentById(id)));
    } catch (PaymentNotFound e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Payment with id " +  id + " not found");
    }
  }
  
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> updatePayment(
      @PathVariable Long id,
      @RequestBody PaymentCreationDto paymentCreationDto
  ) {
   try {
    PaymentDto paymentDto = PaymentDto.fromEntity(
      paymentService.updatePayment(id, paymentCreationDto.toEntity()));

    return ResponseEntity.ok(paymentDto);
   } catch (PaymentNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Payment with id " +  id + " not found");
   }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> deletePayment(@PathVariable Long id) {
    paymentService.deletePayment(id);
    return ResponseEntity.ok("Payment with id " + id + " deleted");
  }
}
