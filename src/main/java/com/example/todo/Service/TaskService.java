package com.example.todo.Service;


import com.example.todo.Model.Task;
import com.example.todo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskService{
    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }



    public void addTask(Task task) throws ParseException {
        task.setCreatedAt(getDate());
      String startdate =  task.getStartAt();
      String endDate = task.getDueAt();
        LocalDateTime localstrtdate = LocalDateTime.parse(startdate);
        LocalDateTime localduedate = LocalDateTime.parse(endDate);
       String lsd = localstrtdate.format(DateTimeFormatter.ofPattern("hh:mm a | dd-MMM"));
      String ldd =  localduedate.format(DateTimeFormatter.ofPattern("hh:mm a | dd-MMM"));
        task.setStartAt(lsd);
       task.setDueAt(ldd);
        taskRepository.save(task);
    }


    public void addEditedTask(Task task){
        task.setUpdatedAt(getDate());
        String startdate =  task.getStartAt();
        String endDate = task.getDueAt();
        LocalDateTime localstrtdate = LocalDateTime.parse(startdate);
        LocalDateTime localduedate = LocalDateTime.parse(endDate);
        String lsd = localstrtdate.format(DateTimeFormatter.ofPattern("hh:mm a | dd-MMM"));
        String ldd =  localduedate.format(DateTimeFormatter.ofPattern("hh:mm a | dd-MMM"));
        task.setStartAt(lsd);
        task.setDueAt(ldd);
        taskRepository.save(task);
    }


    public List<Task> listOfAllTask(){
        return taskRepository.findAll();
    }

    public List<Task> listOfTaskInProgress(){
        return taskRepository.findAllByProgress();
    }

    public List<Task> listOfTaskPending(){
        return taskRepository.findAllByPending();
    }

    public List<Task> listOfTaskCompleted(){
        return taskRepository.findAllByCompleted();
    }


    public void removeTask(Long id){
        Task task =  taskRepository.findById(id).get();
        taskRepository.delete(task);
    }

    public Task getTask(Long id){
       return taskRepository.findById(id).get();
    }

    public String getDate() {
        LocalDateTime dateTime = LocalDateTime.now();
       return dateTime.format(DateTimeFormatter.ofPattern("hh:mm a | dd-MMM"));
    }
}
