package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Payment;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.payment.PaymentNotFound;
import com.scholario.scholario_demo.repository.PaymentRepository;

import com.scholario.scholario_demo.validation.paymentValidation.services.PaymentValidation;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final StudentService studentService;
  private final PaymentValidation paymentValidation;

  @Autowired
  public PaymentService(PaymentRepository paymentRepository,
      StudentService studentService, PaymentValidation paymentValidation) {
    this.paymentRepository = paymentRepository;
    this.studentService = studentService;
    this.paymentValidation = paymentValidation;
  }

  public Payment createPayment(Long studentId, Payment payment) {
    Student student = studentService.getStudentById(studentId);

    payment.setStudentPayments(student);

    paymentValidation.validatePayment(payment);

    return paymentRepository.save(payment);
  }

  public Payment getPaymentById(Long id) throws PaymentNotFound {
    return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFound("Payment not found"));
  }

  public Payment updatePayment(Long id, Payment payment) {
    Payment paymentFound = getPaymentById(id);

    paymentValidation.validatePayment(payment);

    BeanUtils.copyProperties(payment, paymentFound, "id", "studentPayments");

    return paymentRepository.save(paymentFound);
  }

  public List<Payment> getAllPayments(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Payment> paymentPage = paymentRepository.findAll(pageable);

    return paymentPage.toList();

  }

  public void deletePayment(Long id) {
    paymentRepository.deleteById(id);
  }

}
