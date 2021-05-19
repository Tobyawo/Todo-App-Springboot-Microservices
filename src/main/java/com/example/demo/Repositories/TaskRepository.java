package com.example.demo.Repositories;

import com.example.demo.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public final String FIND_ALL_BY_STATUS_PROGRESS = "SELECT * from task where status = 'In-Progress'";
    @Query(value = FIND_ALL_BY_STATUS_PROGRESS, nativeQuery = true)
    public List<Task> findAllByProgress();


    public final String FIND_ALL_BY_STATUS_COMPLETED = "SELECT * from task where status = 'Completed'";
    @Query(value = FIND_ALL_BY_STATUS_COMPLETED, nativeQuery = true)
    public List<Task> findAllByCompleted();


    public final String FIND_ALL_BY_STATUS_PENDING = "SELECT * from task where status = 'Pending'";
    @Query(value = FIND_ALL_BY_STATUS_PENDING, nativeQuery = true)
    public List<Task> findAllByPending();



}
