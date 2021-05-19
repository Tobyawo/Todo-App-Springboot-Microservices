//package com.example.demo.Controller;
//
//import com.example.demo.Model.Userb;
//import com.example.demo.Repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//
//public class HelloWorld {
//    private Userb user;
//    private UserRepository userRepo;
//@Autowired
//public HelloWorld(UserRepository userRepo){
//    this.userRepo = userRepo;
//
//}
//
// @RequestMapping("/")   //takes in what each met
//    public String firstMethod(){
//    Userb newUser = new Userb();
//     newUser.setFirst_name("ola");
//     newUser.setLast_name("Niyi");
//     newUser.setEmail("ola@com");
//     newUser.setPhone_number("080");
//     newUser.setSex("Male");
//    userRepo.save(newUser);
//        return "index";
//    }
//
//}
