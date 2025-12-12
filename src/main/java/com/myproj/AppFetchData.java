package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppFetchData {


    private static SessionFactory sessionFactory;

    Alien obj;

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

// Fetch and print the well-formated data from the table - Need to give implementation to toString() method of the entity class

        Alien alien = (Alien) session.get(Alien.class, 101);
        System.out.println(alien);

        tx.commit();
    }
}
