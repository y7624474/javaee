package com.tw.core.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by cheny on 7/9/15.
 */
public final class HibernateUtil {
    private HibernateUtil(){}
    private static SessionFactory sessionFactory;
    static{
        Configuration cfg = new Configuration();
        cfg.configure();

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        sessionFactory = cfg.buildSessionFactory(builder.build());
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
