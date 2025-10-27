package com.cascade;

import com.map.Answer;
import com.map.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class CascadeExample {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session s = factory.openSession();

        Question q1 = new Question();;
        q1.setQuestionId(888);
        q1.setQuestion("what is cascading");

        Answer a1 = new Answer(1212, "it is a important concept");
        Answer a2 = new Answer(1213, "second answer");
        Answer a3 = new Answer(1214, "third answer");

        List<Answer> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);

        q1.setAnswers(list);
        Transaction tx = s.beginTransaction();
        s.save(q1);

        // if Cascade.ALL is not used in Question class like below then using s.save() for all three answere
        // otherwise declare cascade .. along with questions, answeres will also get saved.
        // ALL means any operation on parent class will be done on child also
        // lile create, update, delete etc
//        @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//        private List<Answer> answers;

        s.save(a1);
        s.save(a2);
        s.save(a3);

        tx.commit();
        s.close();
        factory.close();


    }

}
