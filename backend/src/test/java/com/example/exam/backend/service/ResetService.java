package com.example.exam.backend.service;

import com.example.exam.backend.entity.Copy;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional
public class ResetService {
    @PersistenceContext
    private EntityManager entityManager;

    public void resetDatabase() {
        Query query = entityManager.createNativeQuery("DELETE FROM users_roles");
        query.executeUpdate();

        deleteEntities(Copy.class);
        deleteEntities(Item.class);
        deleteEntities(Users.class);
    }

    private void deleteEntities(Class<?> entity) {
        if (entity == null || entity.getAnnotation(Entity.class) == null) {
            throw new IllegalArgumentException("Invalid non-entity class");
        }

        String name = entity.getSimpleName();

        Query query = entityManager.createQuery("DELETE FROM " + name);
        query.executeUpdate();
    }

}