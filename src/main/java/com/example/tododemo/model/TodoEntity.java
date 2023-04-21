package com.example.tododemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity  // If you do not specify a name, consider the class's name as a name
@Table(name = "Todo")  // If you do not add an annotation or specify a name, consider the name of @Entity as a table name
public class TodoEntity {
  @Id  // Primary key
  @GeneratedValue(generator="system-uuid")  // For auto ID generate
  @GenericGenerator(name="system-uuid", strategy = "uuid")  // Specify generator
  private String id;
  private String userId;
  private String title;
  private boolean done;
}
