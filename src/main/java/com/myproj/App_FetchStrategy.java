package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.sql.ast.tree.expression.AliasedExpression;

import java.util.List;

public class App_FetchStrategy {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        /*Gadget gadget = new Gadget();
        gadget.setGid(1);
        gadget.setGname("Power Key");

        Kid kid = new Kid();
        kid.setKid(101);
        kid.setKname("Rohit");

        gadget.setKid(kid);
        kid.getGadgets().add(gadget);*/


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

        /*session.save(gadget);
        session.save(kid);*/

        // Select query triggered for Kid table is excluding Gadget entities while fetching from DB.
        /**
         * the query to Db is only for Kid table
         * but when the gadget is fetched using the Kid reference, hibernate again hits to gadget table with a Join query or a query to
         * gadget table with condition upon kid. - This type of load or fetching is called `Lazy Loading`.
         *
         * Now to fetch all the related entities, we need to mention fetch strategy in the entity class to the related filed.
         * Annotate the entity with fetch strategy like this- `@OneToMany(mappedBy = "kid", fetch = FetchType.EAGER)`.
         */

        Kid kid = session.get(Kid.class, 101);
        System.out.println(kid.toString());

        List<Gadget> gadgetList = kid.getGadgets();
        System.out.println("Gadget List - "+gadgetList);
        System.out.println("gadget name- "+ gadgetList.get(0).getGname());



        tx.commit(); // or session.getTransaction().commit();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}