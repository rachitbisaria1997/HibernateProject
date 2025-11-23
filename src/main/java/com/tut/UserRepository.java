package com.tut;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User createUser(User user){
        entityManager.persist(user);
        return user;
    }

    public User getById(Long id){
        return entityManager.find(User.class, id);
    }

    public List<User> getAll(){
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    public void deleteUser(Long id){
        User user = entityManager.find(User.class, id);
        if(user != null){
            entityManager.remove(user);
        }
    }

//    EntityManager is the standard JPA interface used for managing persistence.
//    Session is the Hibernate-specific implementation with many additional features.
//    It has first level caching, defined by JPA. it has limited bulk operations
}
