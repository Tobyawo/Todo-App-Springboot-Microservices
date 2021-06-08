package com.example.todo.Service;

import com.example.todo.Model.User;
import com.example.todo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void save(User user) { userRepository.save(user);
    }


    public User get(long id){
        return userRepository.findById(id).get();}



    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(long userid) {
        Optional<User> newuser = userRepository.findById(userid);
        return newuser;
    }


    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}

