package com.example.exam.frontend.controller;



import com.example.exam.backend.entity.Copy;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import com.example.exam.backend.service.CopyService;
import com.example.exam.backend.service.ItemService;
import com.example.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class ItemCopyController implements Serializable {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    private Long itemId;


    public List<Item> getItems(int numberOfItems){
        return itemService.getAllItems(true).stream().limit(numberOfItems).collect(Collectors.toList());
    };

//    public List<Item> getRandomItems(int numberOfItems){
//        return itemService.getRandomItems(numberOfItems, true).stream().limit(numberOfItems).collect(Collectors.toList());
//    }


    public String openLootBox(String userID){
        itemService.openLootBox(userID);
        return "/user.xhtml?faces-redirect=true&successFull=true";
    }

    public String millItem(Long copyId, String userId){
        copyService.millCopy(copyId, userId);
        return "/user.xhtml?faces-redirect=true&successFull=true";
    }

    public void sellLootBox(Long itemID, String userID) {
        itemService.sellLootBox(itemID, userID);
    }


    public Item getItem(Long id){
        return itemService.getItem(id, true);
    }



}
