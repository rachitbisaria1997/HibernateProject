package com.states;

import com.tut.Certificate;
import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StateDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Student student = new Student();
        student.setId(1414);
        student.setName("lata");
        student.setCity("bihar");
        student.setCertificate(new Certificate("java hibernate course", "2 months"));
        // above is transient state

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(student);
        // persistent state. obj is associated with DB and session
        // in persistent state, obj value can be updated
        student.setName("john");

        session.delete(student);
        // Now student object is in "Removed" state.
        // Hibernate will delete it from DB on commit.
        tx.commit();

        session.close();

        student.setName("lanu");
        // this is detached state, value will not get updated in DB as session is closed

        factory.close();


    }
}
