package com.example.demo.Repositories;

import com.example.demo.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

}
