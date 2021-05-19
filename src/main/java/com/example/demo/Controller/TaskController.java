package com.example.demo.Controller;


import com.example.demo.Model.Task;
import com.example.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }



    @GetMapping("/todoHome")
    public ModelAndView TodoHomePage(ModelAndView mav) throws ParseException {
        mav = new ModelAndView("todos");
        List<Task> taskList = taskService.listOfAllTask();
        Task task = new Task();
        mav.addObject("Task",task);
        mav.addObject("taskList",taskList);
        mav.addObject("endDate", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"));
        return mav;
    }

    @PostMapping("/addTask")
    public String TodoHome(@ModelAttribute("Task") Task task){
        taskService.addTask(task);
        return "redirect:/todoHome";
    }
    @RequestMapping(path = "/viewTask/{id}", method = RequestMethod.GET)
    public String viewTodo(Model model, @PathVariable Long id){
        model.addAttribute("taskById", taskService.getTask(id));
        return "taskInfo";
    }

    @GetMapping("/addTask")
    public ModelAndView addnewTaskHome(ModelAndView mav){
        mav = new ModelAndView("AddNewTask2");
        Task task = new Task();
        mav.addObject("Task",task);
        List<Task> taskList = taskService.listOfAllTask();
        mav.addObject("taskList",taskList);
        return mav;
    }

    @PostMapping("/addEditedTask")
    public String editTaskHome(@ModelAttribute("Task") Task task){
        if(task.getStatus().equalsIgnoreCase("Completed")) {
            task.setCompletedAt(taskService.getDate());
            taskService.addEditedTask(task);
        }
        else {
            taskService.addEditedTask(task);
        }
        return "redirect:/todoHome";
    }

    @RequestMapping("/editTask/{id}")
    public ModelAndView editTask(@PathVariable (name= "id") Long id){
        ModelAndView mav = new ModelAndView("newEditTask");
        List<Task> taskList = taskService.listOfAllTask();
        mav.addObject("taskList",taskList);
        Task task = taskService.getTask(id);

        mav.addObject("Task",task);
        return mav;
    }




    @GetMapping("/filterTask")
    public ModelAndView filterTask(Model model, @RequestParam(value = "search", required = false) String status){
        ModelAndView mav = new ModelAndView("todos");
        if(status.equals("pending")){
        List<Task> pendingTaskList = taskService.listOfTaskPending();
        mav.addObject("taskList",pendingTaskList);
        return mav;}
        if(status.equals("In-Progress")){
            List<Task> progressTaskList = taskService.listOfTaskInProgress();
            mav.addObject("taskList",progressTaskList);
            return mav;
        }
        if(status.equals("Completed")){
            List<Task> completedTaskList = taskService.listOfTaskCompleted();
            mav.addObject("taskList",completedTaskList);
            return mav;
        }
        else{
            List<Task> listOfAllTask = taskService.listOfAllTask();
            mav.addObject("taskList",listOfAllTask);
            return mav;
        }
    }


    @RequestMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.removeTask(id);
        return "redirect:/todoHome";
    }


//    @GetMapping
//    public String countdown(Model model) throws ParseException {
//        model.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"));
//        return "index";
//    }


}
