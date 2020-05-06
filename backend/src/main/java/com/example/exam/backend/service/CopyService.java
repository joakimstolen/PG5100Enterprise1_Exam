package com.example.exam.backend.service;

import com.example.exam.backend.entity.Copy;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class CopyService {

    @Autowired
    private EntityManager entityManager;

    public List<Copy> getAllCopies(){
        TypedQuery<Copy> query = entityManager.createQuery("SELECT c FROM Copy c", Copy.class);
        return query.getResultList();
    }

    public List<Copy> filterCopyByItem(Long itemId){
        TypedQuery<Copy> query = entityManager.createQuery("SELECT c FROM Copy c WHERE c.itemInformation.id =?1", Copy.class);
        query.setParameter(1, itemId);

        return query.getResultList();
    }



    public Long newCopy(Long itemId, String userId){
        Item item = entityManager.find(Item.class, itemId);
        Users users = entityManager.find(Users.class, userId);

        if (item == null){
            throw new IllegalArgumentException("Item not found");
        }

        if (users == null){
            throw new IllegalArgumentException("User not found");
        }

        Copy copy = new Copy();
        copy.setPurchasedBy(users);
        copy.setItemInformation(item);
        users.getLootBoxesList().add(item);
        entityManager.persist(copy);

        return copy.getId();
    }


    public List<Copy> filterCopyByUser(String userId){
        TypedQuery<Copy> query = entityManager.createQuery("SELECT c FROM Copy c WHERE c.purchasedBy.userID =?1", Copy.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }
}
