package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class AppQueryLevelCache {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

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

        // create query
        Query q1 = session.createQuery("from alien where aid = 101");
        q1.setCacheable(true);

        Alien alien = (Alien) q1.uniqueResult();
        System.out.println(alien);
        session.close();

        /*
        get different session object session1, fetching same data.
         */

        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();


        Query q2 = session1.createQuery("from alien where aid = 101");
        q2.setCacheable(true);

        Alien alien1 = (Alien)q2.uniqueResult();
        System.out.println(alien1);
        session1.close();

        // In this way it hits query one time only.


    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}