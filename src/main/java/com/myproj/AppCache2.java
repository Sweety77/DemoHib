package com.myproj;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.CriteriaDefinition;
import org.hibernate.query.criteria.spi.CriteriaBuilderExtension;
import org.hibernate.query.sql.internal.SQLQueryParser;
import org.hibernate.transform.Transformers;

import javax.xml.crypto.dsig.Transform;
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

           /* Query query = session.createQuery("from StudentHQL where rollno=7");
            StudentHQL student = (StudentHQL) query.uniqueResult();

                System.out.println(student);*/

            /*Query query = session.createQuery("select * from StudentHQL where rollno=7");// gives error- query.SyntaxException: At 1:7 and token '*', no viable alternative at input
            StudentHQL student = (StudentHQL) query.uniqueResult();

            System.out.println(student);*/

            /*Query query = session.createQuery("select rollno, name, marks from StudentHQL where rollno=7");// gives error-ClassCastException Object cannot be cast to StudentHQL. Moreover, what we get here is not only Object but Object conataining list or all columns as object in the array or Object.
            Object[] student = (Object[]) query.uniqueResult();

            for (Object o : student){
                System.out.println(o);
            }
//            System.out.println(student[0] +" : " + student[1] +" : "+student[2]);
*/

            // we can also use Alias.
            /*Query query = session.createQuery("select s.rollno, s.name, s.marks from StudentHQL s");// gives error-ClassCastException Object cannot be cast to StudentHQL. Moreover, what we get here is not only Object but Object conataining list or all columns as object in the array or Object.
            List<Object[]> students = (List<Object[]>) query.list();

            for (Object[] student : students){

                    System.out.println(student[0] +" : "+student[1]+" : "+student[2]);
            }*/

            /*Query query = session.createQuery("select s.rollno, s.name, s.marks from StudentHQL s where s.marks>50");// gives error-ClassCastException Object cannot be cast to StudentHQL. Moreover, what we get here is not only Object but Object conataining list or all columns as object in the array or Object.
            List<Object[]> students = (List<Object[]>) query.list();

            for (Object[] student : students){

                System.out.println(student[0] +" : "+student[1]+" : "+student[2]);
            }*/

            /**
             * One of a cool features in HQL is the use of variable in query
             * lets say, we have marks as b.
             * In JDBC we create obj of PreparedStatement for vars and set.
             * Unlike in JDBC, we just pass in the var in the query followed by a colon.
             */

            int b=50;

            /*Query query = session.createQuery("select sum(marks) from StudentHQL s where s.marks> :b");
            query.setParameter("b", b);
            List student = (List) query.list(); -- dont need list here just one value is returned

            for (Object marks : student){

                System.out.println(marks);
            }*/

            /*Query query = session.createQuery("select sum(marks) from StudentHQL s where s.marks> :b");
            query.setParameter("b", b);
            Long marks = (Long) query.uniqueResult();

                System.out.println(marks);*/


            /**
             * use SQL in Hibernate - also, called as native query
             * In Native query pass two arguments 1- query string 2 - Entity class of which the result set is gonna returned/expected.
             */

            // start here - see latest changes to print value
            Query query = session.createNativeQuery("select name, marks from studenthql where marks>?");
            query.setParameter(1, 50);
            query.setResultListTransformer(Transformers.aliasToBean(StudentHQL.class));

            List students = query.list();

            for (Object s : students){
                System.out.println(s.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }


    }
}