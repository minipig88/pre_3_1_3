package com.example.pre_3_1_3.dao;

import com.example.pre_3_1_3.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("from Role where roleName = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

}
