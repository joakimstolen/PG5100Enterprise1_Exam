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

    public List<Item> getRandomItems(int numberOfItems){
        return itemService.getRandomItems(numberOfItems, true).stream().limit(numberOfItems).collect(Collectors.toList());
    }

    public String getItemRedirectionLink(Long itemId){
        this.itemId = itemId;
        return "/details.jsf?itemId=" + itemId + "&faces-redirect=true";
    }

    public Long millItem(Long itemId, String userId){
        return copyService.millCopy(itemId, userId);
    }

    public void sellLootBox(Long itemID, String userID) {
        itemService.sellLootBox(itemID, userID);
    }


    public Item getItem(Long id){
        return itemService.getItem(id, true);
    }

    public String makeCopy(String userId){

        if (true){
            copyService.newCopy(itemId, userId);
            return "details?itemId=" + itemId + "&isCopied=true&faces-redirect=true";
        } else {
            return "details?itemId=" + itemId + "&isCopied=false&faces-redirect=true";
        }
    }

}
