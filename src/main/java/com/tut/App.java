package com.tut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;


public class App {

    public static void main(String[] args) throws IOException {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        // creating object of student class
        Student st = new Student();
        st.setId(102);
        st.setCity("Gurgaon");
        st.setName("mohak");
        System.out.println(st);

        Student st1 = new Student();
        st1.setId(101);
        st1.setCity("noida");
        st1.setName("rachit");

        // creating object of address class
        Address address = new Address();
        address.setStreet("street 1");
        address.setCity("delhi");
        address.setOpen(true);
        address.setAddedDate(new Date());
        address.setX(1234.234);

        FileInputStream fis = new FileInputStream("src/main/java/r1.png");
        byte[] data = new byte[fis.available()];
        fis.read(data);
        address.setImage(data);


        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(st);
        session.save(st1);
        session.save(address);

        tx.commit();
        session.close();
        System.out.println("done");

    }
}
