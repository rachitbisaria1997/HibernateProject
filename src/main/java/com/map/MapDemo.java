package com.map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MapDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Question q1 = new Question();
        q1.setQuestionId(1212);
        q1.setQuestion("what is java");

        Answer answer = new Answer();
        answer.setAnswerId(343);
        answer.setAnswer("java is a programming language");
        answer.setQuestion(q1);

        Answer answer1 = new Answer();
        answer1.setAnswerId(344);
        answer1.setAnswer("with the help of java we can build apps");
        answer1.setQuestion(q1);

        List<Answer> list = new ArrayList<>();
        list.add(answer);
        list.add(answer1);

        q1.setAnswers(list);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(q1);
        session.save(answer);
        session.save(answer1);

        tx.commit();

        Question q = (Question) session.get(Question.class, 1212);
        System.out.println(q.getQuestion());
        System.out.println(q.getQuestionId());

        System.out.println(q.getAnswers().size());
        // by default answers will be loaded Lazy. means when getter is called on question
        // q.getAnsers() and q.getAnswers().size() than only data will be loaded / SQL query will be fired for fetching answer
        //data
        // if eager loading, answer data will get loaded along with questions data

        for(Answer a : q.getAnswers()){
            System.out.println(a.getAnswer());
        }
        System.out.println(q.getAnswers().size());

        session.close();
        factory.close();


    }

}
