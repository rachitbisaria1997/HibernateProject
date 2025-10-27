package com.criteria;

import com.tut.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaExample {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Student.class);

        criteria.add(Restrictions.eq("city", "mumbai")); // this will act as where condition

        List<Student> students = criteria.list();

        for(Student s : students){
            System.out.println(s);
        }


        //It allows you to build dynamic queries in Java code using objects rather than string-based queries.
        //
        //Safer than HQL/SQL because it avoids syntax errors (checked at compile-time).
        //
        //Very useful when query conditions depend on user input (e.g., optional filters).

        session.close();

    }
}
