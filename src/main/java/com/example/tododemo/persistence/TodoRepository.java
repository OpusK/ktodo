package com.example.tododemo.persistence;

import com.example.tododemo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// See Aspect Oriented Programming (AOP) for information on 
// how to operate without implementation classes when it is an interface

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
  List<TodoEntity> findByUserId(String userId);

  // ?1 is the position ofthe parameter of method
  @Query("SELECT t FROM TodoEntity t WHERE t.userId = ?1")
  TodoEntity findByUserIdQuery(String userId);

}
