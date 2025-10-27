package com.tut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Student student1 = new Student();
        student1.setId(123);
        student1.setName("ankit");
        student1.setCity("jaipur");

        Certificate certificate = new Certificate();
        certificate.setCourse("android");
        certificate.setDuration("2 months");

        student1.setCertificate(certificate);

        Student student2 = new Student();
        student2.setId(124);
        student2.setName("nikhil");
        student2.setCity("goa");

        Certificate certi = new Certificate();
        certi.setCourse("web develop");
        certi.setDuration("5 months");

        student2.setCertificate(certi);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(student1);
        session.save(student2);

        tx.commit();
        session.close();
        factory.close();

    }

}
