package com.scholario.scholario_demo.entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
  @SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", initialValue = 2234568, allocationSize = 1)
  private Long id;

  private String paymentMethod;
  private String paymentDate;
  private Double paymentValue;
  private String paymentStatus;
  private String paymentType;
  private String paymentDescription;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student studentPayments;

  public Payment() {
  }

  public Payment(String paymentMethod, String paymentDate, Double paymentValue, 
  String paymentStatus, String paymentType, String paymentDescription) {
    this.paymentMethod = paymentMethod;
    this.paymentDate = paymentDate;
    this.paymentValue = paymentValue;
    this.paymentStatus = paymentStatus;
    this.paymentType = paymentType;
    this.paymentDescription = paymentDescription;
  }
  
}
