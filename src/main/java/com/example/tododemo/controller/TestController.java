package com.example.tododemo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.tododemo.dto.ResponseDTO;
import com.example.tododemo.dto.TestRequestBodyDTO;

import java.util.ArrayList;
import java.util.List;

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

  @GetMapping("/testRequestBody")
  public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
    return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
  }

  @GetMapping("/testResponseEntity")
  public ResponseEntity<?> testControllerResponseBody() {
    List<String> list = new ArrayList<>();
    list.add("Hello World! I'm ResponseEntity");
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.badRequest().body(response);
    // return ResponseEntity.ok().body(response);
  }

}
