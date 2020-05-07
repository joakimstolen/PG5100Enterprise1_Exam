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
import java.util.ListIterator;

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


//
//
//        for (int i = 0; i < copyList.size(); i++){
//            Long copy = copyList.get(i).getItemInformation().getId();
//
//            if(copy.equals(itemId)){
//                users.getCopies();
//            }
//        }

        List<Copy> copyList = users.getCopies();

        boolean duplicate = false;
        for (ListIterator<Copy> iterator = copyList.listIterator(); iterator.hasNext();){
            Copy copy = iterator.next();
            if (copy.getItemInformation().getId().equals(itemId)){
                int i = copy.getDuplicates();
                i++;
                copy.setDuplicates(i);
                duplicate = true;
            }
        }

        if (!duplicate){
            Copy copy = new Copy();
            copy.setItemCopyOwner(users);
            copy.setItemInformation(item);
            copy.setDuplicates(1);
            entityManager.persist(copy);
            return copy.getId();
        }

        return null;

    }


    public Long millCopy(Long copyId, String userId){
        Copy copy = entityManager.find(Copy.class, copyId);
        Users user = entityManager.find(Users.class, userId);

        if (copy == null){
            throw new IllegalArgumentException("Copy does not exist");
        }

        long currency = user.getCurrency() + copy.getItemInformation().getPrice();
        int duplicates = copy.getDuplicates();

        //deleteCopy(copy.getId());

        if (duplicates <= 1){
            deleteCopy(copy.getId());

        } else {
            duplicates--;
            copy.setDuplicates(duplicates);
        }

        long newCurrency = user.setCurrency(currency);

        return newCurrency;

    }

    public void deleteCopy(Long copyId){
        Copy copyToRemove = entityManager.find(Copy.class, copyId);

        if (copyToRemove == null){
            throw new IllegalArgumentException("No such pokemon found");
        }

        entityManager.remove(copyToRemove);
    }





    public List<Copy> filterCopyByUser(String userId){
        TypedQuery<Copy> query = entityManager.createQuery("SELECT c FROM Copy c WHERE c.itemCopyOwner.userID =?1", Copy.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }
}
