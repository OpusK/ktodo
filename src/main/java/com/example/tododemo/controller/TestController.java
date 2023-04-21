package com.example.tododemo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/{id}")
  public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
    return "Hello World! ID " + id;
  }

  // host:port/test/testRequestParam?id=123
  @GetMapping("/testRequestParam")
  public String testControllerRequestParam(@RequestParam(required = false) int id) {
    return "Hello World! ID " + id;
  }

}
