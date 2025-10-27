package com.tut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        // get, load
        Student student = (Student) session.get(Student.class, 104);
        System.out.println(student);

        Address ad = (Address)session.get(Address.class, 1);
        System.out.println(ad.getCity() + "  "+ ad.getStreet());

        Address ad1 = (Address) session.get(Address.class, 1);
        System.out.println(ad1.getCity() + "  "+ ad1.getStreet());
        // here result is cached, wont hit db again


        Student student1 = (Student) session.load(Student.class, 107);
        System.out.println(student1);
        // above throws Exception in thread "main" org.hibernate.ObjectNotFoundException as data does not exists

        session.close();
        factory.close();


    }

}
