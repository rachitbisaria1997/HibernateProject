package com.pagination;

import com.tut.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class HQLPagination {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session s = factory.openSession();
        Query query = s.createQuery("from Student", Student.class);

        // pagination
        query.setFirstResult(0);
        query.setMaxResults(5);

        List<Student> list = query.getResultList();
        for(Student student : list){
            System.out.println(student.getName() + "  " + student.getCity());
        }


        s.close();
        factory.close();

    }

}
