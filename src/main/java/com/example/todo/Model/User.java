package com.example.todo.Model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private Long userid;
    private String userName;
    private String email;
    private String password;



    public User() {
    }

    public User(Long userid, String firstname, String lastname, String email, String password) {
        this.userid = userid;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
