package com.myproj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Random;

public class AppCache2 {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        try {
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure()  // looks for hibernate.cfg.xml in classpath
                            .build();

            Metadata metadata = new MetadataSources(registry).buildMetadata();

            sessionFactory = metadata.buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Random random = new Random();

            /*for (int i=1; i<=50; i++){
                StudentHQL s = new StudentHQL();
                s.setRollno(i);
                s.setName("Name "+i );
                s.setMarks(random.nextInt(100));

                session.save(s);

            }
            session.getTransaction().commit();
*/
           /* Query query = session.createQuery("from StudentHQL");
            List<StudentHQL> l = query.list();

            for (StudentHQL s : l){
                System.out.println(s);
            }*/

           /* Query query = session.createQuery("from StudentHQL where marks>50");
            List<StudentHQL> l = query.list();

            for (StudentHQL s : l){
                System.out.println(s);
            }*/

            Query query = session.createQuery("from StudentHQL where rollno=7");
            StudentHQL student = (StudentHQL) query.uniqueResult();

                System.out.println(student);


        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }


    }
}