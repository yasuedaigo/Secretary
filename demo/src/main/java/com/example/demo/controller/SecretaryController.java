package com.example.demo.controller;

import com.example.demo.model.SecretaryForm;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(types = SecretaryForm.class)
public class SecretaryController {

  @ModelAttribute("secretaryForm")
  public SecretaryForm createForm() {
    SecretaryForm secretaryForm = new SecretaryForm();
    return secretaryForm;
  }

  @GetMapping("/secretary")
  public String secretary(SecretaryForm secretaryForm) {
    return "secretary";
  }

  @PostMapping("/secretaryResult")
  public String secretaryResult(SecretaryForm secretaryForm) {
    secretaryForm.calc();
    return "secretaryResult";
  }

}