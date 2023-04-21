package com.example.tododemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tododemo.dto.ResponseDTO;
import com.example.tododemo.dto.TodoDTO;
import com.example.tododemo.model.TodoEntity;
import com.example.tododemo.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

  @Autowired
  private TodoService service;

  @GetMapping("/test")
  public ResponseEntity<?> testTodo() {
    String str = service.testService();
    List<String> list = new ArrayList<>();
    list.add(str);
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping
  public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
    try {
      String temporaryUserId = "temporary-user"; // temporary user id.
      TodoEntity entity = TodoDTO.toEntity(dto);
      // This is because there should be no id at the time of creation.
      entity.setId(null);
      // It sets the temporary user ID. 
      // Currently, there are no authentication and authorization functions,
      // so only one temporary-user can use the application without logging in
      entity.setUserId(temporaryUserId);
      List<TodoEntity> entities = service.create(entity);
      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping
  public ResponseEntity<?> retrieveTodoList() {
    String temporaryUserId = "temporary-user";
    List<TodoEntity> entities = service.retrieve(temporaryUserId);
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
    return ResponseEntity.ok().body(response);
  }

  @PutMapping
  public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
    String temporaryUserId = "temporary-user";
    TodoEntity entity = TodoDTO.toEntity(dto);
    entity.setUserId(temporaryUserId);
    List<TodoEntity> entities = service.update(entity);
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
    return ResponseEntity.ok().body(response);
  }

}
