package com.tw.core.entity;

import org.hibernate.annotations.Table;
import javax.persistence.*;
/**
 * Created by twer on 7/11/15.
 */
@Entity
//@Table(name="User")
public class User {


    private String email;
    private String username;
    private String password;
    private Integer idUser;
//    private Employee emp;


//
//    @OneToOne
//    @JoinColumn(name="idUser")
//    public Employee getEmp() {
//        return emp;
//    }
//
//    public void setEmp(Employee emp) {
//        this.emp = emp;
//    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
