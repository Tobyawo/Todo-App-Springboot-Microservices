package com.example.demo.Controller;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    //1
    @RequestMapping(path = "/")
    public String indexPage(Model model){
        Task task = new Task();
        model.addAttribute("Task",task);
        return "NewIndex";
    }


    @GetMapping("/login")  // when a request link to loginup page is clicked on. it come here
    public String showloginForm(Model model) {
        User user = new User(); // A User object to represent the information in the form.
        model.addAttribute("user", user);
        model.addAttribute("invalid", null);
        return "loginPage";
    }


    //2
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
            return "loginPage";
        }
        httpSession.setAttribute("user", user1);
        return "redirect:/todoHome";
    }

    @GetMapping("/signUp")
    public String showSignUpForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

//        List<String> listGender = Arrays.asList("Male", "Female"); //a List of Gender that will be used to display a select box/dropdown list in the form.
//        model.addAttribute("listGender", listGender);

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
