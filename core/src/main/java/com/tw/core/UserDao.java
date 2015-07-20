package com.tw.core;

import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cheny on 7/8/15.
 */
@Repository
public class UserDao {

    public void addUser(User user) {
        commituser(user, "add");
    }


    public void deleteUser(User user) {
        commituser(user, "delete");
    }

    public List<User> queryAllUsers() {

        String hql = "FROM User";
        Session session = HibernateUtil.getSession();
        List<User> users = session.createQuery(hql).list();
        session.close();
        return users;

    }


    public void commituser(User user, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if(method=="add")
            session.save(user);
        if(method=="delete")
            session.delete(user);


        session.getTransaction().commit();
        session.close();
    }

    public void addEmp(Employee emp) {
        commitEmp(emp, "add");
    }


    public void delEmp(Employee emp) {
         commitEmp(emp, "delete");
    }

    public List<Employee> queryAllEmUsers() {

        String hql = "FROM Employee";
        Session session = HibernateUtil.getSession();
        List<Employee> emps = session.createQuery(hql).list();
        session.close();
        return emps;

    }

    public void commitEmp(Employee emp, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if(method=="add")
            session.save(emp);
        if(method=="delete")
            session.delete(emp);


        session.getTransaction().commit();
        session.close();
    }

    public void addCls(Classinfo cls) {
        commitCls(cls, "add");
    }


    public void delCls(Classinfo cls) {
        commitCls(cls, "delete");
    }

    public List<Classinfo> queryAllCls() {

        String hql = "FROM Classinfo";
        Session session = HibernateUtil.getSession();
        List<Classinfo> cls = session.createQuery(hql).list();
        session.close();
        return cls;

    }

    public void commitCls(Classinfo cls, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if(method=="add")
            session.save(cls);
        if(method=="delete")
            session.delete(cls);


        session.getTransaction().commit();
        session.close();
    }


    public void addCus(Customer cus) {
        commitCus(cus, "add");
    }

    public void delCus(Customer cus) {
        commitCus(cus, "delete");
    }

    public List<Customer> queryAllCus() {

        String hql = "FROM Customer";
        Session session = HibernateUtil.getSession();
        List<Customer> cus = session.createQuery(hql).list();
        session.close();
        return cus;

    }

    public void commitCus(Customer cus, String method) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if(method=="add")
            session.save(cus);
        if(method=="delete")
            session.delete(cus);


        session.getTransaction().commit();
        session.close();
    }
}
