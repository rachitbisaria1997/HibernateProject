package com.mapping.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Person person = new Person(23, "rachit", "agra", "1224");

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(person);

        transaction.commit();
        session.close();
        factory.close();


        // this will use XML file to create a entity in database
    }
}
