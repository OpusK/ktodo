package com.example.tododemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("test")
public class TestController {

  @GetMapping
  public String testController() {
    System.out.println("Testing");
    return "Hello World!";
  }
  
}
