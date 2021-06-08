package com.example.todo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @Column(name="taskId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String taskTodo;
    private String taskDescription;
    private String createdAt;
    private String updatedAt;
    private String completedAt;
    private String startAt;
    private String dueAt;
    private String status;



}
