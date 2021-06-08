package com.example.todo.Controller;


import com.example.todo.Model.Task;
import com.example.todo.Model.User;
import com.example.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@EnableScheduling
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

////    @Scheduled(cron="*/10 * * * * ?")
//    @Scheduled(fixedDelay = 10000, initialDelay = 60000)
//    public void taskSchedule()
//    {
//      System.out.println("Method executed at every(10sec after startup)  and then 10 seconds onward . Current time is :: "+ new Date());
//    }

    @GetMapping("/todoHome")
    public ModelAndView TodoHomePage(ModelAndView mav, HttpSession httpSession, Model model) throws ParseException {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
        mav = new ModelAndView("todos");
        List<Task> taskList = taskService.listOfAllTask();
        String momentdate = taskService.getDate();
        Task task = new Task();
        mav.addObject("Task",task);
        mav.addObject("taskList",taskList);
        mav.addObject("moment", momentdate);
             return mav;}
        else{mav = new ModelAndView("NewIndex");
            return mav;}
    }

    @PostMapping("/addTask")
    public String TodoHome(@ModelAttribute("Task") Task task, HttpSession httpSession) throws ParseException {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
        taskService.addTask(task);
        return "redirect:/todoHome";}
        else{ return "redirect:/";}
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


    @GetMapping
    public String countdown(Model model) throws ParseException {
        model.addAttribute("endDate", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01"));
        return "index";
    }


}
