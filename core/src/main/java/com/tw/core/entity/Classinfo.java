package com.tw.core.entity;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;

import java.util.Date;

/**
 * Created by twer on 7/19/15.
 */
public class Classinfo {

    private String classname;
    private String coach;
    private String time;
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
