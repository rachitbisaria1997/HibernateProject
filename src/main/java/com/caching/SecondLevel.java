package com.caching;

import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SecondLevel {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session1 = factory.openSession();

        Student student1 = session1.get(Student.class, 101);
        System.out.println(student1);
        session1.close();


        Session session2 = factory.openSession();
        Student student2 = session2.get(Student.class, 101);
        System.out.println(student2);


        // only one time SQL query will be fired
        session2.close();

    }
}
