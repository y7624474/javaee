package com.tw.core;

import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.internal.*;
import org.hibernate.criterion.Restrictions;
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
        if (method == "add")
            session.save(user);
        if (method == "delete")
            session.delete(user);


        session.getTransaction().commit();
        session.close();
    }

    public static <T> T getById(Class<T> clazz, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        T t = (T) session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    public static <T> T getBynum(Class<T> clazz, int num) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM User as c where c.num='" + num + "'";
        List<User> users = session.createQuery(hql).list();
        int id = 0;
        for (User user : users) {
            if (user.getIdUser() == num) {
                id = user.getIdUser();
            }


        }
        T t = (T) session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return t;
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
        if (method == "add")
            session.save(emp);
        if (method == "delete")
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

    public void updateCls(Classinfo cls) {
        commitCls(cls, "update");
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
        if (method == "add")
            session.save(cls);
        if (method == "delete")
            session.delete(cls);
        if (method == "update")
            session.update(cls);

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
        if (method == "add")
            session.save(cus);
        if (method == "delete")
            session.delete(cus);


        session.getTransaction().commit();
        session.close();
    }


    public List<Classinfo> queryPriCls(String strcoach) {

        Session session = HibernateUtil.getSession();

        //  HQL查询
        //  HQL是hibernate自己的一套查询语言，于SQL语法不同，具有跨数据库的优点,缺点：是适用于hibernate框架

        String hql = "FROM Classinfo as c where c.coach='"+strcoach+"'";
        //  String hql = "FROM Classinfo as c where c.coach='"+coach+"' and c.time='2015-07-09'";
        List<Classinfo> pricls = session.createQuery(hql).list();
        session.close();
        return pricls;


        //对象化查询Criteria
        //Criteria是一种比hql更面向对象的查询方式,革新了以前的数据库操作方式，易读 缺点：适用面较HQL有限
//
//        Criteria c=session.createCriteria(Classinfo.class);
//        c.add(Restrictions.eq("coach",strcoach));
//        List<Classinfo> pricls = c.list();
//        session.close();
//        return pricls;

    }

//    public static List queryPriCls(String strcoach) {
//
//        Session session = HibernateUtil.getSession();
//
//        //动态分离查询DetachedCriteria
//        //DetachedCriteria类使你在一个session范围之外创建一个查询，并且可以使用任意的 Session来执行它。面向对象操作，分离业务与底层，不需要字段属性摄入到Dao实现层。  缺点：适用面较HQL有限
//
////        DetachedCriteria dc = DetachedCriteria.forClass(Classinfo.class);
////        dc.add(Restrictions.eq("coach",strcoach));
////        List classinfo=dc(dc);
////        return classinfo;
//
//        //sql查询
//        // 如果不熟悉HQL，不可以转数据库平台，万能方法   缺点：破坏跨平台，不易维护，不面向对象。
//
//        SQLQuery q =  session.createSQLQuery("select * from class where coach='"+strcoach+"'").addEntity(Classinfo.class);
//        List<Classinfo> rs = q.list();
//        session.close();
//        return rs;
//
//
//        //命名查询
//        //万能方法，有点像ibatis轻量级框架的操作，方便维护。  缺点：不面向对象。基于hql和sql，有一定缺陷
//        SQLQuery q = session.getNamedQuery("getListByCoach");
//        return q.list();
//    }

    static List dc(DetachedCriteria dc) {

        Session session = HibernateUtil.getSession();
        Criteria c = dc.getExecutableCriteria(session);
        List rs = c.list();
        session.close();
        return rs;
    }



    public List<Classinfo> queryPriTimeCls(String time) {

        String hql = "FROM Classinfo as c where c.time='" + time + "'";
        Session session = HibernateUtil.getSession();
        List<Classinfo> pricls = session.createQuery(hql).list();
        session.close();
        return pricls;

    }

    public List<Employee> queryCoach() {

        String hql = "FROM Employee as c where c.role='coach'";
        Session session = HibernateUtil.getSession();
        List<Employee> coach = session.createQuery(hql).list();
        session.close();
        return coach;

    }
}
