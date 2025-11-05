package com.caching;

import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FirstDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();

        Student student = session.get(Student.class, 101);
        System.out.println(student);

        Student student1 = session.get(Student.class, 101);
        System.out.println(student1);
        // only 1 time SQL query will be fired.
        // this is fitst level caching

        session.close();
        factory.close();

    }

}
