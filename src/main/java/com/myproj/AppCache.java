package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppCache {

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

//        session.save(alien);

        /**
         * Here, application hit select query to db.
         */
        Alien alien = session.get(Alien.class,101L);
        System.out.println(alien);

        /*
        when again ask for same data then hibernate application doesnot hit the query again but fetches the data from its local
        memory called - Cache.
         */

        Alien alien2 = session.get(Alien.class,101L);
        System.out.println(alien2);
        session.close();

        /*
        But if the first session is closed and again the same data is asked. The hibernate application again hits the db
        because the 1st level cache is available session wise. It is not available to cross - sessions.
        It can be understood from logs when run teh application.
         */


        Session session1 = sessionFactory.openSession();

        Transaction tx1 = session1.beginTransaction();
        Alien alien1 = session1.get(Alien.class, 101);// nothing found in local cache (First level) coz the earlier session was closed so, hits Db for query again .
        System.out.println(alien1);

//        tx.commit(); // or session.getTransaction().commit();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}