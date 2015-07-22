package com.tw.core.entity;

//import org.hibernate.annotations.Table;
import javax.persistence.*;
/**
 * Created by twer on 7/11/15.
 */
@Entity
@Table(name="User")
public class User {


    private String email;

    private String username;

    private String password;

    private Integer idUser;


    private Employee emp;



    @Id
    @GeneratedValue
    @Column(name = "idUser")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToOne()
    @JoinColumn(name="num")
    public Employee getEmp() {
        return emp;
    }
    public void setEmp(Employee emp) {
        this.emp = emp;
    }
}
