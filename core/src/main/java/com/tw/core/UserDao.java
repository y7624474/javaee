package com.tw.core;

import com.tw.core.entity.User;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cheny on 7/8/15.
 */
//@Repository
public class UserDao {

    public void addUser(User user) {
       // Session session = HibernateUtil.getSessionFactory().openSession();

        commit(user, "add");
    }


    public void deleteUser(User user) {
       // Session session = HibernateUtil.getSessionFactory().openSession();
        commit(user, "delete");

    }

    public List<User> queryAllUsers() {
        System.out.println("~~~~~~~~~~~~~~");

        String hql = "FROM User";
        Session session = HibernateUtil.getSession();
        List<User> users = session.createQuery(hql).list();
        session.close();
        return users;

    }

    public void commit(User user, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if(method=="add")
            session.save(user);
        if(method=="delete")
            session.delete(user);


        session.getTransaction().commit();
        session.close();
    }



}
