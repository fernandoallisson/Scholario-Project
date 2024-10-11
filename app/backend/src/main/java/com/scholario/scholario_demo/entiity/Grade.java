// package com.scholario.scholario_demo.entiity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "grades")
// public class Grade {

//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long id;

//   private double gradeValue;
//   private String date;

//   public Grade() {
//   }

//   public Grade(double gradeValue, String date) {
//     this.gradeValue = gradeValue;
//     this.date = date;
//   }

//   public Long getId() {
//     return id;
//   }

//   public void setId(Long id) {
//     this.id = id;
//   }

//   public double getGradeValue() {
//     return gradeValue;
//   }

//   public void setGradeValue(double gradeValue) {
//     this.gradeValue = gradeValue;
//   }

//   public String getDate() {
//     return date;
//   }

//   public void setDate(String date) {
//     this.date = date;
//   }
// }
