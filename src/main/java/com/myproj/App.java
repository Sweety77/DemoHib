package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Session is an Interface - so cannot create object. So we can use SessionFactory Interface.
 * Configuration - through configuration object, can call the buildSessionFactory() method.
 * open a session using sessionFactory object.
 */



public class App {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Alien obj = new Alien();
        obj.setAid(103);
        obj.setAname("Sarang");
        obj.setColor("While");

        try {
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure()  // looks for hibernate.cfg.xml in classpath
                            .build();

            Metadata metadata = new MetadataSources(registry).buildMetadata();

            sessionFactory = metadata.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }


        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.save(obj);

        tx.commit();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}