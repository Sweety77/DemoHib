package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        laptop.setLname("lenovo");
        laptop.setLid(123);

        Student student = new Student();
        student.setName("Sweety");
        student.setRollno(1612);
        student.setMarks(98);
        student.getLaptop().add(laptop);

//        laptop.setStudent(student);
        laptop.getStudent().add(student);

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
        session.save(laptop);

        tx.commit(); // or session.getTransaction().commit();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}