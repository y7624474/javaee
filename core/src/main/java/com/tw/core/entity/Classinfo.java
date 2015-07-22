package com.tw.core.entity;
import javax.persistence.*;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;

import java.util.Date;

/**
 * Created by twer on 7/19/15.
 */
@Entity
@Table(name="Class")
public class Classinfo {

    @Column(name = "classname")
    private String classname;

    @Column(name = "coach")
    private String coach;

    @Column(name = "time")
    private String time;

    @Id
    @GeneratedValue
    @Column(name = "idClass")
    private Integer idClass;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }
}
