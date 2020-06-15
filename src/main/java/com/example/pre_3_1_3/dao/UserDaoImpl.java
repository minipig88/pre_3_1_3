package com.example.pre_3_1_3.dao;

import com.example.pre_3_1_3.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return entityManager.createQuery("from User where username = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
