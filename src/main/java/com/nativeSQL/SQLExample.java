package com.nativeSQL;

import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class SQLExample {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();

        String sql = "SELECT * FROM student WHERE city = :city";

        //A native query is a query written in plain SQL (specific to your database) instead of HQL.
        //You need complex queries that are difficult to express in HQL/Criteria.

        NativeQuery<Student> query = session.createNativeQuery(sql, Student.class); // map result to entity
        query.setParameter("city", "mumbai");

        List<Student> studentList = query.getResultList();
        for(Student s : studentList){
            System.out.println(s.getName() + "  " + s.getId());
        }

        session.close();
        factory.close();

    }

}
