package com.tw.core.entity;

//import org.hibernate.annotations.Table;

import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Id;

/**
 * Created by twer on 7/16/15.
 */

@Entity
@Table(name="Employee")
public class Employee {
    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;


    @Id
    @Column(name = "idEmployee")
    private Integer idEmployee;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }
}
