package com.example.demo.model;

import lombok.Data;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class SecretaryForm {
  private final int MALE_EXTRA_CHARGE = 1000;
  private final int FREE = 0;
  private final int NONE = 0;
  private int totalPrice;
  private int numberOfMale;
  private int numberOfFemale;
  private int maleOfPayment;
  private int femaleOfPayment;
  public boolean femaleFirst;
  public Map<Boolean, String> modes;
  public int change;

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
    if (femaleFirst) {
      calcOfFemaleFirst();
      calcChange();
      return;
    }
    calcOfEquality();
    calcChange();
  }

  public void calcOfEquality() {
    int payment = totalPrice / getTotalNumberOfPeople();
    maleOfPayment = payment;
    femaleOfPayment = payment;
    noneSexReset();
  }

  public void calcOfFemaleFirst() {
    if (isExcessCharge()) {
      numberOfFemale = NONE;
      calcOfEquality();
      femaleOfPayment = FREE;
      noneSexReset();
      return;
    }
    maleOfPayment = MALE_EXTRA_CHARGE + (totalPrice - getTotalChargePrice())
        / getTotalNumberOfPeople();
    femaleOfPayment = maleOfPayment - MALE_EXTRA_CHARGE;
    noneSexReset();
  }

  private boolean isExcessCharge() {
    if (totalPrice < (MALE_EXTRA_CHARGE * numberOfMale)) {
      return true;
    }
    return false;
  }

  private int getTotalNumberOfPeople() {
    return numberOfMale + numberOfFemale;
  }

  private int getTotalChargePrice() {
    return MALE_EXTRA_CHARGE * numberOfMale;
  }

  private void noneSexReset() {
    if (numberOfMale == NONE) {
      maleOfPayment = FREE;
    }
    if (numberOfFemale == NONE) {
      femaleOfPayment = FREE;
    }
  }

  private void calcChange(){
    int totalMalePrice = maleOfPayment * numberOfMale;
    int totalFemalePrice = femaleOfPayment * numberOfFemale;
    change = totalPrice - (totalMalePrice + totalFemalePrice);
  }
}