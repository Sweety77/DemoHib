package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Session is an Interface - so cannot create object. So we can use SessionFactory Interface.
 * Configuration - through configuration object, can call the buildSessionFactory() method.
 * open a session using sessionFactory object.
 */



public class App_EMbeddable {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {


        /**
         * Embeddable Laptop for which table will not be created.
         * Here will only save student and the columns for Laptop table will be created in the Student
         * table.
         */
        Laptop_Emb laptopEmb = new Laptop_Emb();
        laptopEmb.setLname("lenovo");
        laptopEmb.setLid(123);



        Student student = new Student();
        student.setName("Sweety");
        student.setRollno(1612);
        student.setMarks(98);
        student.setLaptopEmb(laptopEmb);

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

        // Save/persist the data to the table
        session.save(student);
//        session.save(laptopEmb);

        tx.commit(); // or session.getTransaction().commit();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}