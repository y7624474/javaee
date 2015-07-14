package com.tw.core.entity;

/**
 * Created by twer on 7/11/15.
 */

public class User {

    private String username;

//    public String getName(){
//        return username;
//    }
//    public void setName(String username){
//        this.username=username;
//    }

    public User(){

    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
