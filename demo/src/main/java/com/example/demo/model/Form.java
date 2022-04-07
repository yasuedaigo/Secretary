package com.example.demo.model;

import lombok.Data;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Form {
  private final int MALE_EXTRA_CHARGE = 1000;
  private int totalPrice;
  private int numberOfMale;
  private int numberOfFemale;
  private int maleOfPayment;
  private int femaleOfPayment;
  public boolean femaleFirst;
  public Map<Boolean, String> modes;

  public Map<Boolean, String> getModes() {
    modes = new LinkedHashMap<Boolean, String>();
    modes.put(true, "女性優遇");
    modes.put(false, "平等");
    return modes;
  }

  public String getTextOFMode() {
    return modes.get(femaleFirst);
  }

  public void calc() {
    if (this.femaleFirst) {
      calcOfFemaleFirst();
      return;
    }
    calcOfEquality();
  }

  public void calcOfEquality() {
    int payment = this.totalPrice / this.getTotalNumberOfPeople();
    this.maleOfPayment = payment;
    this.femaleOfPayment = payment;
  }

  public void calcOfFemaleFirst() {
    this.maleOfPayment = this.MALE_EXTRA_CHARGE + (this.totalPrice - this.getTotalChargePrice())
        / this.getTotalNumberOfPeople();
    this.femaleOfPayment = this.maleOfPayment - this.MALE_EXTRA_CHARGE;
  }

  private int getTotalNumberOfPeople() {
    return this.numberOfMale + this.numberOfFemale;
  }

  private int getTotalChargePrice() {
    return this.MALE_EXTRA_CHARGE * this.numberOfMale;
  }
}