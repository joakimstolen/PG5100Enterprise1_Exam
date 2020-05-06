package com.example.exam.backend.service;

import com.example.exam.backend.entity.Item;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ItemService {

    @Autowired
    private EntityManager entityManager;

    public List<Item> getAllItems(boolean withBuyers){
        TypedQuery query = entityManager.createQuery("SELECT i FROM Item i ORDER BY i.price ASC", Item.class);

        List<Item> allItems = query.getResultList();

        if (withBuyers){
            allItems.forEach(u -> u.getAllItemBuyers().size());
        }

        return allItems;
    }


    public Long createItem(String name, String description, String type, Long price){
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setType(type);
        item.setPrice(price);

        entityManager.persist(item);

        return item.getId();
    }


    public List<Item> getItems(){
        TypedQuery query = entityManager.createQuery("SELECT i FROM Item i", Item.class);
        return query.getResultList();
    }


    public Item getItem(long itemId, boolean withBuyers){
        Item item = entityManager.find(Item.class, itemId);

        if (item == null){
            throw new IllegalArgumentException("No item found");
        }

        if (withBuyers){
            item.getAllItemBuyers().size();
        }

        return item;
    }


    public List<Item> getRandomItems(int amount, boolean withCopies){
        List<Item> items = new ArrayList<>(amount);
        while (items.size() < amount){
            items.add(getRandomItem(withCopies));
        }

        return items;
    }


    public Item getRandomItem(boolean withCopies){

        TypedQuery<Long> sizeQuery = entityManager.createQuery("SELECT COUNT(item) FROM Item item", Long.class);
        long size = sizeQuery.getSingleResult();

        Random random = new Random();
        int rnd = random.nextInt((int)size);

        TypedQuery<Item> query = entityManager.createQuery("SELECT item FROM Item item", Item.class).setFirstResult(rnd).setMaxResults(1);

        Item item = query.getSingleResult();

        if (withCopies){
            Hibernate.initialize(item.getAllItemBuyers());
        }

        return item;

    }



    public void deleteItem(Long itemId){
        Item itemToRemove = entityManager.find(Item.class, itemId);

        if (itemToRemove == null){
            throw new IllegalArgumentException("No such pokemon found");
        }

        entityManager.remove(itemToRemove);
    }

    public List<Item> filterItemsByName(String name){
        TypedQuery<Item> query = entityManager.createQuery("SELECT i FROM Item i WHERE i.name =?1 ORDER BY i.name ASC", Item.class);
        query.setParameter(1, name);

        return query.getResultList();
    }

}
