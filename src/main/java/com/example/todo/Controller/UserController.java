package com.example.todo.Controller;

import com.example.todo.Model.Task;
import com.example.todo.Model.User;
import com.example.todo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping(path = "/")
    public String indexPage(Model model){
        Task task = new Task();
        model.addAttribute("Task",task);
        return "NewIndex"; }


    @GetMapping("/login")
    public String showloginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("invalid", null);
        return "loginPage"; }


    @RequestMapping(value = "/loginPost", method= RequestMethod.POST)
    public String checkUserlogin(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        User user1 = service.getUserByEmail(user.getEmail());
        if (user1 == null) {
            model.addAttribute("invalid", "User does not exist. check login details or register.");
            return "loginPage";
        }
        user1 = service.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user1 == null) {
            model.addAttribute("invalid", "Incorrect password");
            return "loginPage"; }
        httpSession.setAttribute("user", user1);
        return "redirect:/todoHome";
    }


    @GetMapping("/logout")
    public String loggingOut(Model model, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "redirect:/login";
    }




    @GetMapping("/signUp")
    public String showSignUpForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "SignUp";
    }

    @PostMapping("/handleSignUp")
    public String submitSignUpForm(@ModelAttribute("user") User user) {
        service.save(user);

        ModelAndView modelAndView = new ModelAndView("loginPage");
        modelAndView.addObject("user",user);
        return "loginPage";
    }


}
