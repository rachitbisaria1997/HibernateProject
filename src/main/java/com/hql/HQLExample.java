package com.hql;

import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class HQLExample {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();

        String query = "from Student where city = :x";

        Query q  = session.createQuery(query);
        q.setParameter("x", "noida");

        List<Student> list = q.getResultList();
        for(Student s : list){
            System.out.println(s.getName() + "  "+ s.getId());
        }
        System.out.println("---------------------");

        // delete statement
        Transaction tx = session.beginTransaction();
        Query q2 = session.createQuery("delete from Student s where s.city=:c");
        q2.setParameter("c", "Gurgaon");

        // it gives no of executions
        int r = q2.executeUpdate();
        System.out.println("deleted");

        // update statement
        Query q3 = session.createQuery("update Student set city = :c where name = :n");
        q3.setParameter("c", "mumbai");
        q3.setParameter("n", "rachit");

        int r1 = q3.executeUpdate();

        System.out.println(r1);

        tx.commit();
        session.close();
        factory.close();

    }
}
