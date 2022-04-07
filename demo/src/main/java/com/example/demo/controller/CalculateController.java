package com.example.demo.controller;

import com.example.demo.model.Form;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(types = Form.class)
public class CalculateController {

  @ModelAttribute("Form")
  public Form createForm() {
    Form form = new Form();
    return form;
  }

  @GetMapping("/calculate")
  public String form(Form form) {
    return "index";
  }

  @PostMapping("/result")
  public String result(Form form) {
    form.calc();
    return "result";
  }

}