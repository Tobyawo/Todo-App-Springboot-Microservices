package com.example.todo.Repositories;

import com.example.todo.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

}
